package Tools;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;



public class Judge {
	public static boolean TokenJudge_old(String id,String token,HttpSession httpSession)
	{//TODO 利用令牌判断用户是否合法
		boolean result=false;
		String val=(String)httpSession.getAttribute(id);
		if((val!=null)&&(val.equals(token)))
		{
			result=true;
		}
		return result;
	}
	public static boolean TokenJudge(String id,String token,ServletContext servletContext)
	{
		boolean result=false;
		//先判断是否在线
		String rtoken= (String) servletContext.getAttribute(id);
		//System.out.println("rtoken"+rtoken);
		if(rtoken !=null & token.equals(rtoken))
		{//验证通过，修改时间
			
			//System.out.println("checked");
			@SuppressWarnings("unchecked")
			HashMap<String, Long> LastReqTime=(HashMap<String, Long>) servletContext.getAttribute("OnlineUsers");
			LastReqTime.put(id, new Date().getTime());
			servletContext.setAttribute("OnlineUsers", LastReqTime);
			result=true;
		}
		return result;
	}
	public static boolean LockJudge(String id)
	{//TODO 判断用户是否被锁
		boolean result=false;
		return result;
	}
	public static void main(String[] args) {
		System.out.println();
	}
	
}
