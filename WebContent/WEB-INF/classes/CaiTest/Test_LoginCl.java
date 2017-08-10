package CaiTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Tools.Conndb;
import Tools.DesUtils;
import Tools.Token;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Test_LoginCl
 */
@WebServlet("/Test_LoginCl")
public class Test_LoginCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger=LogManager.getLogger(Test_LoginCl.class);//获取logger, error>warn>info, info会打印出所有error,warn和info的信息
	private boolean ture;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = null ;//定义Writer用于返回数据
		Connection conn=null;
		try {
			pw = resp.getWriter();//从HttpServletResponse获取Writer
			pw.println("welcome");//返回welcome在网页上
			conn = new Conndb().getConn();//获取数据库链接
			PreparedStatement pStatement= (PreparedStatement) conn.prepareStatement("select *from users where uname='huanuo'");//将要执行的查询语句写入PreparedStatement
			ResultSet rs = pStatement.executeQuery();//执行查询
			//int colnum=rs.getMetaData().getColumnCount();
			if(rs.next())//如果查询到数据，说明数据库链接没有问题
			{
				//for (int i = 1; i <= colnum; i++) {
	             //   System.out.print(rs.getString(i) + "\t");
	             //}
				pw.println("Successfully connected to database");
			}
			else {
			
				pw.println("can't find your selection");
			}
			pStatement.close();//关闭PreparedStatement
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//System.out.println("未能连接数据库");
			pw.println("Opps!Unsuccessfully connected to database");
		}finally
		{
			try {
				conn.close();//关闭Connection
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.flush();
			pw.close();
		}	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		resp.setContentType("text/html;charset=utf-8"); //定义响应内容的格式类型 
        req.setCharacterEncoding("utf-8");  //定义接受内容编码格式
        resp.setCharacterEncoding("utf-8"); //定义相应内容编码格式
		String ret=null;
		JSONObject retJson=new JSONObject();
		String uname=null;
		String passWd=null;
		String webPassword=null;
		//PrintWriter pw=null;
		//PrintWriter pw=null;
		Connection conn=null;
		PreparedStatement pStatement=null;
		PreparedStatement pStatement1=null;
	    //String name ="";
	    String token="";
	    int num=0;
		try {
			uname = req.getParameter("username");//获取用户名
			//logger.error("errortest");
			//logger.info("infotest");
			//logger.warn("warntest");
			passWd = req.getParameter("Password");//获取密码
			webPassword=req.getParameter("webPassword");//获取网页测试版的密码
			DesUtils des = new DesUtils("leemenz");//自定义密钥
			String rPassword=null;
			if (webPassword!=null)//为了兼容网页测试，如果获取到网页版的密码则不做解密处理
			{
				rPassword=webPassword;
				logger.info("Web Loging....");
			}
			else {
				rPassword=des.decrypt(passWd);
				logger.info("Client Loging....");
			}
			logger.info("username= "+uname+" password= "+passWd+" webPassword="+webPassword);
			Conndb conndb = new Conndb();//连接数据库
			logger.info("Connecting to Database...");
			conn = conndb.getConn();
			logger.info("Connected");
	        pStatement= (PreparedStatement) conn.prepareStatement("select *from users where uname='"+uname+"'");
	        logger.info("querying Data...");
			ResultSet rs = pStatement.executeQuery();
			//int colnum=rs.getMetaData().getColumnCount();
			//System.out.println(colnum);
			///////////////////////////////////////////////////////////////////////
			if(rs.next())
			{
				logger.info("Checking locked...");
				if(rs.getString(5).equals("YES")){//查询是否locked状态为Yes
					//pStatement1= (PreparedStatement) conn.prepareStatement("select TIMESTAMPDIFF(DAY,locked_time,CURDATE()) AS time from users where uname='"+uname+"'");
			        //logger.info("querying Data...");
					//ResultSet rs1 = pStatement1.executeQuery();				
				ret="-5";//This user is locked
				logger.info("This user is locked...");
				//pw = resp.getWriter();
				//pw.println("This user is locked");
				}
				else{
				logger.info("Checking Password...");
				if(rs.getString(3).equals(rPassword))
				{
					ret="1";//Login Success!
					//颁发令牌 返回json{"code":"xx","id":"xxxx","token":"xxxxxxxxx"}
					logger.info("Generating Token...");
					Token loginToken=new Token(uname, rPassword);
					token=loginToken.getToken();
					//将令牌存入ServletContext
					loginToken.putToken(token, this.getServletContext());
					logger.info("User:"+uname+" successfully login");
				}
				else {
					logger.info(uname+":WrongPassword");
					@SuppressWarnings("unchecked")
					////////////////////////////////
					Map<String, Integer> map = (Map<String, Integer>)this.getServletContext().getAttribute("FailNum");
					//PrintWriter pWriter1=resp.getWriter();
					//pWriter1.println("密码输入错误"+num+"次");
					if (map!=null) {						
						boolean contains=map.containsKey(uname);//判断是否包含指定的键值
						if (contains) {//如果条件为真
						num=map.get(uname);//获取键值对应的值
						//PrintWriter pWriter=resp.getWriter();
						//pWriter.println("密码输入错误"+num+"次");
						 }   
					}
					else{
						map = new HashMap<String, Integer>();						
					}
					num++;  
					map.put(uname,num);//
					this.getServletContext().setAttribute("FailNum", map);	
				  if(num<5){
					  //pw=resp.getWriter();
					  logger.info("Password enter error 1, error 5 user will lock...");
				      //pw.println("密码输入错误"+number+"次");
				      ret="-1";
				      //resp.sendRedirect("welcome");	
				      }
				  else{					
					logger.info("The user will be locked...");//pw.println(" "); 
					UserCL update=new UserCL();
					boolean b=update.updateUser(uname);
					ret="-1";
					if (b==ture) {
						UserCL updatetime=new UserCL();//更新用户锁定时间
						boolean c=updatetime.UpdateTime(uname);	
						ret="-5";//The user has been locked;
					}
                   //pw=resp.getWriter();
				   //pw.println("密码输入错误"+num+"次");
		         } 															
				}
	          }
			}
			//////////////////////////////////////////////////////
			else 
			{
				logger.info(uname+":No such a user");
				ret="-2";//No such a user
			}
			retJson.put("code", ret);
			retJson.put("id", uname);
			retJson.put("token",token);
			//pw.println(retJson.toString());
			OutputStream out=resp.getOutputStream();
			out.write(retJson.toString().getBytes());
			//System.out.println(ret);
			logger.info("response to client:"+ret);
			pStatement.close();
			logger.info("PreparedStatement closed");
			out.flush();
			logger.info("printWriter flushed");
			out.close();
			logger.info("printWriter closed");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("exception");
		}finally{
			if(conn!=null)//如果数据库连接不为null 则关闭数据库连接
			{
				try {
				    logger.info("close connection");
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("error to close connection");
				}//释放数据库连接
			}
			//pw.flush();
			//pw.close();	
		}
			
	}
}
