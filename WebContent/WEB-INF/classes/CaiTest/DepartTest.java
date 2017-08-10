package CaiTest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import DataMid.DominoInform;
import Tools.Conndb;
import Tools.Judge;

/**
 * 
 * @author 蔡如男
 * TODO 定义客户端获取部门列表请求的响应
 */
@WebServlet("/DepartTest")
public class DepartTest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger logger=LogManager.getLogger(DepartTest.class);//获取logger
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 重写doPost方法，定义针对Post请求的响应
		// super.doPost(req, resp);
		String departString=null;
		//Connection conn=null;
		PrintWriter out=null;
		JSONObject jsonObject=null;
		PreparedStatement pStatement=null;
		resp.setContentType("text/html;charset=utf-8");  
        req.setCharacterEncoding("utf-8");  
        resp.setCharacterEncoding("utf-8");
		// 
		try {
			String id=req.getParameter("id");
			String token=req.getParameter("token");
			logger.info(id+"'s resquest");
			if(Judge.TokenJudge(id,token, this.getServletContext())){
			logger.info("Token has Passed");
			departString=req.getParameter("department");
			//Mysql
			/*conn = new Conndb().getConn();
			logger.info("get Database Connection");
	        //conn = (Connection) DriverManager.getConnection(url, username, password);
	        pStatement= (PreparedStatement) conn.prepareStatement("select rname from information where department='"+departString+"'order by convert(rname using gbk) asc");
			ResultSet rs = pStatement.executeQuery();
			//jsonObject=new JSONObject();
			int rowCount=0;
			while(rs.next())
			{
					rowCount++;
	                //System.out.print(rs.getString(1) + "\t");
	            
	                jsonObject.put(String.valueOf(rowCount), rs.getString(1));//将数据库中返回的数据以{n,name}的格式存入json，n为排序的顺序
			}
			jsonObject.put("rowcount", rowCount);//将返回的行数放入json
			System.out.println(jsonObject.toString());
			System.out.println(rowCount);
			pStatement.close();*/
			//Domino
			jsonObject=new DominoInform2().getDepart(departString, "depart");
			if(jsonObject !=null)
			{   
				System.out.println("success");
				jsonObject.put("code", "1");
			}else {
				System.out.println("else");
				logger.info("CODE=-5,Nothing returned");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-5");//没从数据库中取到数据
			}
			}
			else {
				System.out.println("else");
				logger.info("CODE=-3,Token has not Passed");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-3");//掉线，需要重新登录	
			}
			
			out=resp.getWriter();
			out.println(jsonObject.toString());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			/*try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("error to close connection");
			}*/
			out.flush();
			out.close();
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 测试此链接的有效性
		//super.doGet(req, resp);
		PrintWriter pWriter=resp.getWriter();
		//JSONObject jsonObject =new JSONObject();
		pWriter.println("DepatmentList");
		pWriter.flush();
		pWriter.close();
	}

}
