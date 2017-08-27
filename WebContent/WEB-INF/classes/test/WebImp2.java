package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebImp2
 */
@WebServlet("/WebImp2")
public class WebImp2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebImp2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<h1>TokenTest1</h1>");
			pw.println("<form action=getTel method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("sname");
			pw.println("<input type=text name=sname>");
			pw.println("<input type=submit value=TEST1>");
			pw.println("</form>");
			pw.println("<h1>TokenTest2</h1>");
			pw.println("<form action=addressList method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("depart");
			pw.println("<input type=text name=depart>");
			pw.println("<input type=submit value=TEST2>");
			pw.println("</form>");
			pw.println("<h1>TokenTest3</h1>");
			pw.println("<form action=LogoffServlet method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("<input type=submit value=Logoff>");
			pw.println("</form>");
			pw.println("<h1>TokenTest4</h1>");
			pw.println("<form action=DataUpdate method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("data");
			pw.println("<input type=text name=data>");
			pw.println("<input type=submit value=update>");
			pw.println("</form>");
			pw.println("<h1>TokenTest5</h1>");
			pw.println("<form action=DocsList method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("tag");
			pw.println("<input type=text name=tag>");
			pw.println("<input type=submit value=getDocList>");
			pw.println("</form>");
			pw.println("<h1>TokenTest6</h1>");
			pw.println("<form action=SearchResp method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("str");
			pw.println("<input type=text name=str>");
			pw.println("<input type=submit value=SearchResp>");
			pw.println("</form>");
			pw.println("<h1>TokenTest7</h1>");
			pw.println("<form action=DepartmentTest method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("aname");
			pw.println("<input type=text name=aname>");
			pw.println("<input type=submit value=通讯录>");
			pw.println("</form>");
			pw.println("<h1>TokenTest8</h1>");
			pw.println("<form action=DepartTest method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("department");
			pw.println("<input type=text name=department>");
			pw.println("<input type=submit value=Test3>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>GetWorkList</h1>");
			pw.println("<form action=WorkList method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("worktype");
			pw.println("<input type=text name=worktype>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("<h1>GetDetailForm</h1>");
			pw.println("<form action=GetApplicationForm method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("worktype");
			pw.println("<input type=text name=worktype>");
			pw.println("docid");
			pw.println("<input type=text name=docid>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("<h1>GetAnno</h1>");
			pw.println("<form action=Announcement method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>GetAnnoList</h1>");
			pw.println("<form action=AnnoList method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("tag");
			pw.println("<input type=text name=tag>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>GetAnnoInfor</h1>");
			pw.println("<form action=AnnoInfor method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("Annotitle");
			pw.println("<input type=text name=Annotitle>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>GetDocmemt</h1>");
			pw.println("<form action=Document method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("Doctype");
			pw.println("<input type=text name=Doctype>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>GetDocList</h1>");
			pw.println("<form action=DocumentList method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("category");
			pw.println("<input type=text name=category>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>DocSearch</h1>");
			pw.println("<form action=DocSearch method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("docsearch");
			pw.println("<input type=text name=docsearch>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			pw.println("</form>");
			pw.println("<h1>GetDocInfor</h1>");
			pw.println("<form action=DocInfor method=post>");
			pw.println("id");
			pw.println("<input type=text name=id>");
			pw.println("token");
			pw.println("<input type=text name=token>");
			pw.println("doctitle");
			pw.println("<input type=text name=doctitle>");
			pw.println("<input type=submit value=submit>");
			pw.println("</form>");
			//////////////////////////////
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
