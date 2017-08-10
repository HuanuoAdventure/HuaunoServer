package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionTest3 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		String id=req.getParameter("id");
		HttpSession session= req.getSession();
		String sessionid=req.getSession().getId();
		session.setMaxInactiveInterval(300);
		String valString=(String)session.getAttribute(id);
		PrintWriter pWriter=resp.getWriter();
		pWriter.println(sessionid+"&"+id+"&"+valString);
		pWriter.flush();
		pWriter.close();
	}
	
}
