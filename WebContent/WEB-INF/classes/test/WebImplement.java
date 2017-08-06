package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class WebImplement extends HttpServlet{
/**
 * @author duxiaohan
 * TODO
 * Login web Test
 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		try {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<h1>loginTest</h1>");
			pw.println("<form action=login method=post>");
			pw.println("username");
			pw.println("<input type=text name=username>");
			pw.println("<br />");
			pw.println("Password");
			pw.println("<input type=text name=webPassword>");
			pw.println("<br />");
			pw.println("<input type=submit value=TEST>");
			pw.println("</form>");
			//////////////////////////////
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
