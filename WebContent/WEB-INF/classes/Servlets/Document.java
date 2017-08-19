package Servlets;

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

import CaiTest.DepartTest;
import CaiTest.DominoInform2;
import Tools.Judge;

/**
 * Servlet implementation class Document
 * @author 蔡如男
 */
@WebServlet("/Document")
public class Document extends HttpServlet {//获取文档首页分类
	private static final long serialVersionUID = 1L;
	Logger logger=LogManager.getLogger(Document.class);//获取logger
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Connection conn=null;
		PrintWriter out=null;
		JSONObject jsonObject=null;
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
			String Doctype=req.getParameter("Doctype");
			//Domino
			jsonObject=new DominoInform2().getDocList(Doctype, "Document");
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
		pWriter.println("Document");
		pWriter.flush();
		pWriter.close();
	}

}