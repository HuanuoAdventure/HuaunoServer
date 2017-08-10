package CaiTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Tools.Judge;

/**
 * Servlet implementation class DepartmentTest
 */
@WebServlet("/DepartmentTest")
public class DepartmentTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger=LogManager.getLogger(DepartmentTest.class);//获取logger实例   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pWriter=resp.getWriter();
		pWriter.println("AddresslistResponse");
		pWriter.flush();
		pWriter.close();		
	}	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		JSONObject jsonObject=null;
		String departString=null;
		//PreparedStatement pStatement=null;
		resp.setContentType("text/html;charset=utf-8");  
        req.setCharacterEncoding("utf-8");  
        resp.setCharacterEncoding("utf-8");
        try {
			String id=req.getParameter("id");
			String token=req.getParameter("token");
			logger.info(id+"'s resquest");
			if(Judge.TokenJudge(id,token, this.getServletContext())){
			logger.info("Token has Passed");
			departString=req.getParameter("aname");
			//Domino
			jsonObject=new DominoInform2().getDepartList(departString);
			if(jsonObject !=null)
			{
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
}