package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import DataMid.DominoWorkList;
import Tools.Judge;
import Tools.Token;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class WorkList
 */
@WebServlet("/WorkList")
public class WorkList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger=LogManager.getLogger(WorkList.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pWriter=response.getWriter();
		pWriter.println("WorkList");
		pWriter.flush();
		pWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String WorkType=null;
		//Connection conn=null;
		PrintWriter out=null;
		JSONObject jsonObject=null;
		Session session=null;
		response.setContentType("text/html;charset=utf-8");  
		request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");
        try {
        	String id=request.getParameter("id");//获取用户的账号
			String token=request.getParameter("token");//获取用户的token
			logger.info(id+"'s resquest");
			System.out.println(id);
			if(Judge.TokenJudge(id,token, this.getServletContext())){//token通过验证
			logger.info("Token has Passed");
			System.out.println("Token has Passed");
			WorkType=request.getParameter("worktype");//获取用户需要的数据类型，在办工作，待办工作还是办结工作
			session=NotesFactory.createSession("192.168.0.38:63148",id,Token.parseToken(token));//解密Token，获取Session
			System.out.println("Got session");
			if(WorkType.equals("Ongoing")){
			try {
				jsonObject=new DominoWorkList().getOngoingWorkList(session, id);
			} catch (Exception e) {
				// TODO: handle exception
				jsonObject.put("CODE", "-8");
			}
			}
			else if(WorkType.equals("ToDo")){
				try {
					jsonObject=new DominoWorkList().getTodoWorkList(session, id);
				} catch (Exception e) {
					// TODO: handle exception
					jsonObject.put("CODE", "-8");
				}
			}
			else if(WorkType.equals("Finished")){
					try {
						jsonObject=new DominoWorkList().getFinishedWorkList(session, id);
					} catch (Exception e) {
						// TODO: handle exception
						jsonObject.put("CODE", "-8");
					}
					}
			
			System.out.println(jsonObject);
			out=response.getWriter();
			out.println(jsonObject.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			if(out!=null){
			out.flush();
			out.close();
			}
			if(session !=null){
			try {
				session.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("session recycle error");
			}
			}
		}
	}

}
