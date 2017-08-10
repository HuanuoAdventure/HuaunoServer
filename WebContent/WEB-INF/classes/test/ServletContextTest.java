package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
/**
 * @author duxiaohan
 * TODO 测试session是否被覆盖
 * 2017年5月5日
 */
public class ServletContextTest extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
//		HttpSession hSession=req.getSession();
//		hSession.setMaxInactiveInterval(100);
//		hSession.setAttribute("801254", "11111111");
//		PrintWriter pWriter=resp.getWriter();
//		pWriter.println("111111111");
//		pWriter.flush();
//		pWriter.close();
		ServletContext sc=this.getServletContext();
		JSONObject jsonObject=new JSONObject();
		Date date=new Date();
		jsonObject.put("token", "12345");
		jsonObject.put("LastReqTime", date.getTime());
		//jsonObject.put("ThisReqTime", date.getTime());
		sc.setAttribute("801254", jsonObject);
		PrintWriter pw=resp.getWriter();
		pw.println(sc.getAttribute("801254"));
		pw.flush();
		pw.close();
		
	}

}
