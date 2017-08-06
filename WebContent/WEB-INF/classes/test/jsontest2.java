package test;

import java.sql.ResultSet;

import net.sf.json.JSONObject;
import JavaBean.InformBean;
import Tools.Conndb;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class jsontest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test2
//		String departString="RSO";
//		Connection conn=null;
//		PreparedStatement pStatement=null;
//		try {
//			Conndb conndb = new Conndb();
//			conn = conndb.getConn();
//	        pStatement= (PreparedStatement) conn.prepareStatement("select rname from information where department='"+departString+"'");
//			ResultSet rs = pStatement.executeQuery();
//			JSONObject jsonObject=new JSONObject();
//			int rowCount=0;
//			while(rs.next())
//			{
//					rowCount++;
//	            
//	                jsonObject.put(String.valueOf(rowCount), rs.getString(1));
//			}
//			jsonObject.put("rowcount", rowCount);
//			System.out.println(jsonObject.toString());
//			
//			pStatement.close();
//			conn.close();
//			
//		} catch (Exception e) {
//			
//		}
		int i =0;
		try {
			Connection con=new Conndb().getConn();
			InformBean informBean=new InformBean(con, "801254");
			JSONObject dataJson=informBean.getJson();
			System.out.println(dataJson.toString());
			String sqlString="update information set"
					+ " gender='"+dataJson.get("gender")
					+ "' ,tel='"+dataJson.getString("tel")
					+ "' ,department='"+dataJson.getString("depart")
					+ "' ,location='"+dataJson.getString("location")
					+ "' ,email='"+dataJson.getString("email")
					+ "' where uname='"+dataJson.getString("id")
					+ "' and rname='"+dataJson.getString("name")+"'";
			PreparedStatement pStatement=(PreparedStatement)con.prepareStatement(sqlString);
			i=pStatement.executeUpdate();

			pStatement.close();
			con.close();
			
			System.out.println(sqlString);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			i=-1;
		}finally
		{
			System.out.println("result="+i);
		}
		
	}

}
