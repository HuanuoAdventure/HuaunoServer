package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import DataMid.DominoInform;
import JavaBean.InformBean;
import Tools.Conndb;
import Tools.Judge;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class GetTel
 */
@WebServlet("/GetTel")
/**
 * 新建此类时选用eclipse中的new Servlet，所以格式细节上可能有所不同
 * @author duxiaohan
 * TODO 为客户端通讯录搜索功能提供服务
 */
public class GetTel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger=LogManager.getLogger(GetTel.class);//获取logger实例   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 测试数据库的联通性
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter=response.getWriter();
		printWriter.println("GetTel");
		Conndb conndb=new Conndb();
		try {
			Connection con=conndb.getConn();
			InformBean informBean=new InformBean(con, "801254");
			printWriter.println(informBean.getJson().toString());
			con.close();
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sname = null;//定义需要查找的人名或者工号
		//Connection con=null;//定义数据库链接
		JSONObject jsonObject=null;//定义返回的Json对象
		PrintWriter out=null;
		response.setContentType("text/html;charset=utf-8");//设置response的http格式
		//System.out.println(request.getContentType());
		request.setCharacterEncoding("utf-8");//设置请求的编码格式，需要与客户端协商
		response.setCharacterEncoding("utf-8");//设置响应的编码格式
		try {
			//判断用户是否合法或者掉线
			String id=request.getParameter("id");//获取客户端发来请求的ID
			String token=request.getParameter("token");//获取token
			System.out.println("id:"+id+"token:"+token);
			logger.info(id+"'s resquest");
			out=response.getWriter();
			if(Judge.TokenJudge(id,token,this.getServletContext() )){//调用Judge类中的TokenJudge方法判断token是否合法
				//System.out.println("if");
			logger.info("Token has Passed");
			sname = request.getParameter("sname");//获取要搜索的用户名或者工号
			//System.out.println(sname);
			logger.info("Searching...:"+sname);
			/**
			 * Mysql
			 */
			/*con=new Conndb().getConn();
			logger.info("get Database Connection");
			InformBean informBean=new InformBean(con, sname);//新建InformBean实例，传递数据库连接和需要查找的用户名
			jsonObject=informBean.getJson();*/
			/**
			 * Domino
			 */
			//判断是工号还是姓名
			String tag=null;
			if(StringUtils.isNumeric(sname))//判断是工号(数字)还是姓名（字符）
			{//工号
				tag="id";
			}else {//姓名
				tag="name";
			}
			jsonObject=new DominoInform().getOneInform(sname, tag);
			if (jsonObject!=null){//成功获取到json
				jsonObject.put("code", "1");
			}
			else {
				logger.info("CODE=-5,Nothing returned");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-5");//数据库连接失败
			}

			}
			else {
				//System.out.println("else");
				logger.info("CODE=-3,Token has not Passed");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-3");//掉线，需要重新登录
			}
			out.println(jsonObject.toString());
			logger.info("Response to Client");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			/*try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("error to close connection");
			}*/
			out.flush();
			out.close();
		}
		
	}

}
