package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONObject;
import Tools.Conndb;
import Tools.Judge;

public class DocsList extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6989177999159275147L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		PrintWriter pw = resp.getWriter();
		pw.println("DocsList");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		//token check
		resp.setContentType("text/html;charset=utf-8");
		System.out.println(req.getContentType());
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		JSONObject jsonObject=null;
		try {
			String id=req.getParameter("id");
			String token=req.getParameter("token");
			String tag=req.getParameter("tag");
			System.out.println("id:"+id+"token:"+token+"tag"+tag);
			if(Judge.TokenJudge(id,token,this.getServletContext() ))
			{//刚进入页面
				Connection con=new Conndb().getConn();
				PreparedStatement pStatement=null;
				String sql=null;
				if(tag.equals("preview"))
				{
					//select *from docs where tag="工作在华诺" limit 3;
					sql="(select name,url from docs where tag='学习在华诺' limit 3) "
							+ "union all (select name,url from docs where tag='工作在华诺' limit 3) "
							+ "union all (select name,url from docs where tag='生活在华诺' limit 3)";
					System.out.println(sql);
					 pStatement= (PreparedStatement) con.prepareStatement(sql);
					 ResultSet rs = pStatement.executeQuery();
					 jsonObject=new JSONObject();
						//jsonObject=new JSONObject();
					   int rowCount=0;
						while(rs.next())
						{
								rowCount++;
				                System.out.print(rs.getString(1) + "\t");
				                jsonObject.put(String.valueOf(rowCount), rs.getString(1));
				                jsonObject.put("url"+String.valueOf(rowCount), rs.getString(2));
						}
						jsonObject.put("rowcount", rowCount);
				}
				else if (tag.equals("learn")) {
					sql="select name,url from docs where tag='学习在华诺'";
					 pStatement= (PreparedStatement) con.prepareStatement(sql);
					 ResultSet rs = pStatement.executeQuery();
					 jsonObject=new JSONObject();
						//jsonObject=new JSONObject();
					   int rowCount=0;
						while(rs.next())
						{
								rowCount++;
				                //System.out.print(rs.getString(1) + "\t");
				                jsonObject.put(String.valueOf(rowCount), rs.getString(1));
				                jsonObject.put("url"+String.valueOf(rowCount), rs.getString(2));
						}
						jsonObject.put("rowcount", rowCount);
				}
				else if(tag.equals("work")) {
					sql="select name,url from docs where tag='工作在华诺'";
					 pStatement= (PreparedStatement) con.prepareStatement(sql);
					 ResultSet rs = pStatement.executeQuery();
					 jsonObject=new JSONObject();
						//jsonObject=new JSONObject();
					   int rowCount=0;
						while(rs.next())
						{
								rowCount++;
				                //System.out.print(rs.getString(1) + "\t");
				                jsonObject.put(String.valueOf(rowCount), rs.getString(1));
				                jsonObject.put("url"+String.valueOf(rowCount), rs.getString(2));
						}
						jsonObject.put("rowcount", rowCount);
				}
				else if (tag.equals("life")) {
					sql="select name,url from docs where tag='生活在华诺'";
					 pStatement= (PreparedStatement) con.prepareStatement(sql);
					 ResultSet rs = pStatement.executeQuery();
					 jsonObject=new JSONObject();
						//jsonObject=new JSONObject();
					   int rowCount=0;
						while(rs.next())
						{
								rowCount++;
				                //System.out.print(rs.getString(1) + "\t");
				                jsonObject.put(String.valueOf(rowCount), rs.getString(1));
				                jsonObject.put("url"+String.valueOf(rowCount), rs.getString(2));
						}
						jsonObject.put("rowcount", rowCount);
			
				}
				pStatement.close();
				con.close();
				jsonObject.put("code", "4");
			}
			else {
				System.out.println("else");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-3");//掉线，需要重新登录
			}
			PrintWriter pw=resp.getWriter();
			pw.println(jsonObject.toString());
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
