package JavaBean;


import java.sql.ResultSet;
import java.sql.SQLException;




import java.util.Vector;

import lotus.domino.Document;
import net.sf.json.JSONObject;





import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author duxiaohan
 * TODO 将从mysql数据库information表中读取的数据封装成类
 */
public class InformBean {

	private String iconurl="";//头像的url地址
	private String name="";//姓名
	private String id="";//工号
	private String tel="";//电话号码
	private String depart="";//部门
	private String email="";//邮箱
	private String gender="";//性别
	private String location="";//项目地
	public InformBean(Vector<String> doc)
	{//TODO 构造函数：从Domino数据库中取出信息
		
	}
	public InformBean(JSONObject dataJsonObject) {
		// TODO 构造函数：从Json中解析出inform类
		name=dataJsonObject.getString("name");
		id=dataJsonObject.getString("id");
		tel=dataJsonObject.getString("tel");
		depart=dataJsonObject.getString("depart");
		email=dataJsonObject.getString("email");
		gender=dataJsonObject.getString("gender");
		location=dataJsonObject.getString("location");
	}
	public InformBean(Connection con,String uname)//con：mysql数据库连接，uname：工号
	{//TODO 构造函数：从Mysql数据库中获取information中的一行
		 PreparedStatement pStatement;
		try {
			//pStatement = (PreparedStatement) con.prepareStatement("select *from information where uname='"+uname+"'");
			pStatement = (PreparedStatement) con.prepareStatement("select *from information where uname='"+uname+"' or rname='"+uname+"'");//要在数据库中执行的查询
			ResultSet rs = pStatement.executeQuery();//执行查询并将结果存放在集合中
			//int colnum=rs.getMetaData().getColumnCount();
			if(rs.next())//如果查询有所返回，则将结果填入变量
			{
				name=rs.getString(3);
				id=rs.getString(2);
				tel=rs.getString(4);
				depart=rs.getString(5);
				email=rs.getString(6);
				gender=rs.getString(8);
				location=rs.getString(9);
				iconurl="http://172.16.201.17:8080/img/icon/"+id+".jpg";
			}
			
			pStatement.close();//关闭PreparedStatement，避免占用资源
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*定义获取私有变量的方法：*/
	public String getName(){
		return name;
	}
	public String getId()
	{
		return id;
	}
	public String getTel()
	{
		return tel;
	}
	public String getDepart()
	{
		return depart;
	}
	public String getEmail()
	{
		return email;
	}
	public String getGender()
	{
		return gender;
	}
	public String getLocation()
	{
		return location;
	}
	public String getIconUrl()
	{
		return iconurl;
	}
	
	public JSONObject getJson(){
		//将变量封装到一个JsonObject，并返回
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("iconurl", iconurl);
		jsonObject.put("name",name);
		jsonObject.put("id", id);
		jsonObject.put("tel", tel);
		jsonObject.put("depart", depart);
		jsonObject.put("email", email);
		jsonObject.put("gender", gender);
		jsonObject.put("location", location);
		return jsonObject;
	}
}
