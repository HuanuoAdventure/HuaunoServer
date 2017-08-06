package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONObject;
import Tools.Conndb;
import Tools.Judge;

public class DataUpdate extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1564705113702127847L;

	/**
	 * 
	 */
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		PrintWriter pw=resp.getWriter();
		pw.println("DataUpdate");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		JSONObject retJsonObject=new JSONObject();
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html;charset=utf-8"); //定义响应内容的格式类型 
        req.setCharacterEncoding("utf-8");  //定义接受内容编码格式
        resp.setCharacterEncoding("utf-8"); //定义相应内容编码格式
		try {
			String id=req.getParameter("id");
			String token=req.getParameter("token");
			int updateResult=0;
			if(Judge.TokenJudge(id,token, this.getServletContext())){
				String dataString=req.getParameter("data");
				System.out.println(dataString);
				@SuppressWarnings("static-access")
				JSONObject dataJson=new JSONObject().fromString(dataString);
				Connection con=new Conndb().getConn();
				//此处需要加一个判断是否get为空
				String sqlString="update information set"
						+ " gender='"+dataJson.get("gender")
						+ "' ,tel='"+dataJson.getString("tel")
						+ "' ,department='"+dataJson.getString("depart")
						+ "' ,location='"+dataJson.getString("location")
						+ "' ,email='"+dataJson.getString("email")
						+ "' where uname='"+dataJson.getString("id")
						+ "' and rname='"+dataJson.getString("name")+"'";
				PreparedStatement pStatement=(PreparedStatement)con.prepareStatement(sqlString);
				updateResult=pStatement.executeUpdate();
				System.out.println("result="+updateResult);
				pStatement.close();
				con.close();
				if(updateResult==1)
				{//修改成功返回code=3
					
					retJsonObject.put("code", "3");
				}
				else
				{
					retJsonObject.put("code","-5");
				}
			}
			else {
				//令牌未通过验证返回code=3
				retJsonObject.put("code", "-3");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//往数据库写入更新失败
			retJsonObject.put("code", "-4");
			//
			
		}finally
		{
		
			out.println(retJsonObject.toString());
			System.out.println(retJsonObject.toString());
			out.flush();
			out.close();
		}
	}
	
}
