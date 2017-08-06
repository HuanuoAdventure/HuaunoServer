package Listeners;

import java.util.Timer;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * @author duxiaohan
 * TODO ServletContextListener对象，可重写Tomcat服务开启时或即将关闭前的操作
 */
public class TimeoutListener implements ServletContextListener{
	Timer timer=new Timer();//定义一个定时器
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContext) {
		// TODO Tomcat服务开启时执行的内容
		timer.schedule(new CheckTimer(servletContext.getServletContext()),0,60000);//定时执行的内容和时间间隔（单位：毫秒）
	}

}
