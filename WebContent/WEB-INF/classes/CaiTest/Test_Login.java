package CaiTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Test_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @author 蔡如男
     * @see HttpServlet#HttpServlet()
     */
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<h1>loginTest</h1>");
			pw.println("<form action=Test_LoginCl method=post>");
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


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
	}

}

