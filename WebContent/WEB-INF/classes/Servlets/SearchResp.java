package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class SearchResp
 */
@WebServlet(description = "搜索关联显示响应", urlPatterns = { "/SearchResp" })
public class SearchResp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger=LogManager.getLogger(SearchResp.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pWriter=response.getWriter();
		pWriter.println("SearchResponse");
		pWriter.flush();
		pWriter.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str = null;//定义需要查找的人名或者工号
		//Connection con=null;//定义数据库链接
		JSONObject jsonObject=null;//定义返回的Json对象
		PrintWriter out=null;
		//PreparedStatement pStatement=null;
		//String sqlString=null;//定义查询语句
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
			str = request.getParameter("str");//获取要搜索的用户名或者工号
				//System.out.println(sname);
				logger.info("Searching...:"+str);
				/**
				 * Mysql
				 */
				/*con=new Conndb().getConn();
				logger.info("get Database Connection");
				
				if(StringUtils.isNumeric(str))//判断是工号(数字)还是姓名（字符）
				{//工号
					
					  //查询51条数据，实际只给客户端返回50条，用来判断结果是否超过50
					 
					logger.info("id");
					sqlString="select uname from information where uname like '"+str+"%' limit 51";
				}else {//姓名
					logger.info("name");
					sqlString="select rname from information where rname like '"+str+"%' limit 51";
				}
			
				System.out.println(sqlString);
				pStatement= (PreparedStatement) con.prepareStatement(sqlString);
				ResultSet rs = pStatement.executeQuery();
				logger.info("sql executed");
				jsonObject=new JSONObject();
				int rowCount=0;
				while(rs.next())
				{
						rowCount++;
		                //System.out.print(rs.getString(1) + "\t");
		                if(rowCount<51){//如果结果超过50条，最后一条不放入返回的json
		                jsonObject.put(String.valueOf(rowCount), rs.getString(1));//将数据库中返回的数据以{n,name}的格式存入json，n为排序的顺序
		                }
				}
				if(rowCount>50)
				{//如果结果超过50条，more=1表示结果超过50，客户端可以显示”加载全部“
					jsonObject.put("rowcount", 50);
					jsonObject.put("more", 1);
				}else {//如果结果不超过50条，more=0表示结果在50条以内，客户端不用显示”加载全部“
					jsonObject.put("rowcount", rowCount);//将返回的行数放入json
					jsonObject.put("more", 0);
				}
				System.out.println(jsonObject.toString());
				System.out.println(rowCount);
			
	
			pStatement.close();
			logger.info("PreparedStatement closed");*/
			//InformBean informBean=new InformBean(con, str);//新建InformBean实例，传递数据库连接和需要查找的用户名
			//jsonObject=informBean.getJson();
			/**
			 * Domino
			 */
			jsonObject=new DominoInform().getInformList(str, "str");
			if(jsonObject !=null){
				jsonObject.put("code", "1");//连接正常，可以获取其他信息
			}
			else
			{
				logger.info("CODE=-5,Nothing returned");
				jsonObject=new JSONObject();
				jsonObject.put("code", "-5");//查询不到数据
			}
			//
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
