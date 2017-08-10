package Listeners;

import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author duxiaohan
 * TODO 检查是否有用户长时间未操作，将其移出在线用户列表
 */
public class CheckTimer extends TimerTask{
	
	private ServletContext sc=null;
	Logger logger =LogManager.getLogger(CheckTimer.class);
	public CheckTimer(ServletContext servletContext) {
		// TODO 构造函数：传递ServletContext对象
		sc=servletContext;
	}
	@Override
	public void run() {
		// TODO 定时执行的内容
		System.out.println("checking....");//Console输出
		logger.info("Timeout Checking....");//添加Log(Log4j)
		@SuppressWarnings("unchecked")
		HashMap<String, Long> LastReqTime=(HashMap<String, Long>) sc.getAttribute("OnlineUsers");//从ServletContext中获取key=OnlineUsers的对象，该对象为一个HashMap类型的在线用户列表，存放的为<在线用户工号，上次操作时间>
		if (LastReqTime !=null)//如果该HashMap不为空
		{
			for(HashMap.Entry<String, Long> entry:LastReqTime.entrySet())  //遍历HashMap
		    {  
		        //System.out.println(entry.getKey()+":"+entry.getValue());  
		        String id = entry.getKey();//获取工号
		        Long LastTime=entry.getValue();//获取上次操作的时间
		        if(sc.getAttribute(id)!=null){//在ServletContext中查询是否有该用户的token，如果有，则说明该用户没退出
		        	if(new Date().getTime()-LastTime>900000)//判断如果用户超时（单位为毫秒）
		        	{
		        		sc.removeAttribute(id);//将其token销毁
		        		System.out.println("remove:"+id);//Console输出
		        		logger.info("remove"+id);//添加Log(Log4j)
		        		LastReqTime.remove(id);//并将其从在线用户列表中删除
		        	}
		        }
		        else {
		        	LastReqTime.remove(id);//如果用户已退出，则只将其从在线用户列表中删除
				}
		    } 
			sc.setAttribute("OnlineUsers", LastReqTime);//重置OnlineUsers用户对象，使刚刚对在线用户列表的操作生效
		}
	}

}
