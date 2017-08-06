package CaiTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**@author 蔡如男
 * Servlet implementation class Welcom
 *
 */
@WebServlet("/welcome")
public class welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String times=this.getServletContext().getAttribute("FailNum").toString();
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw=response.getWriter();
		//ServletContext sc=this.getServletContext();
		//String info=(String)sc.getAttribute("myInfo");
	    pw.println("ServletContext="+times);	
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
