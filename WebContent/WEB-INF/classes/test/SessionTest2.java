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

public class SessionTest2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
//		HttpSession hSession=req.getSession();
//		hSession.setMaxInactiveInterval(100);
//		hSession.setAttribute("801254", "22222222");
//		PrintWriter pWriter=resp.getWriter();
//		pWriter.println("22222222");
//		pWriter.flush();
//		pWriter.close();
		ServletContext scContext =this.getServletContext();
		String token=null;
		Long last=null;
		//Long This=null;
		Long now=new Date().getTime();
		
		JSONObject jsonObject=(JSONObject)scContext.getAttribute("801254");
		if(jsonObject !=null){
		token=(String) jsonObject.get("token");
		last=(Long) jsonObject.get("LastReqTime");
		//This=(Long) jsonObject.get("ThisReqTime");
		if (now-last>10000) {
			//设置一分钟过期 如果过期，直接删除token
			scContext.removeAttribute("801254");
			System.out.println("remove");
			token=null;
			last=null;
		}
		else {
			//如果未过期，则更新上次访问时间为现在
			jsonObject.remove("LastReqTime");
			jsonObject.put("LastReqTime", now);
			System.out.println(now);
			System.out.println(new Date().getTime());
			scContext.setAttribute("801254", jsonObject);
		}
		}
		jsonObject=(JSONObject)scContext.getAttribute("801254");
		if (jsonObject !=null) {
			System.out.println("not null");
		}
		PrintWriter printWriter=resp.getWriter();
		printWriter.println("Token:"+token);
		printWriter.println("LastReqTime:"+last);
		printWriter.println("Now:"+now);
		printWriter.flush();
		printWriter.close();
	}
}
