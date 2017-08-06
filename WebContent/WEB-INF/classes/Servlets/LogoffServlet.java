package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Tools.Judge;

public class LogoffServlet extends HttpServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = -3956417953598999117L;

/**
 * @author duxiaohan
 * TODO For LogOffbutton
 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		PrintWriter pw=resp.getWriter();
		pw.println("Logoff");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		JSONObject jsonObject=null;
		try {
			String id=req.getParameter("id");
			String token=req.getParameter("token");
			//HttpSession httpSession =req.getSession();
			ServletContext sContext=this.getServletContext();
			if(Judge.TokenJudge(id,token, sContext)){
				//httpSession.removeAttribute(id);
				sContext.removeAttribute(id);
				jsonObject=new JSONObject();
				jsonObject.put("code", "2");//退出成功 令牌销毁
			}
			else {
				System.out.println("else");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-3");//掉线，需要重新登录
			}
			//OutputStream out=resp.getOutputStream();
			PrintWriter out=resp.getWriter();
			//out.write(jsonObject.toString().getBytes());
			out.println(jsonObject.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
