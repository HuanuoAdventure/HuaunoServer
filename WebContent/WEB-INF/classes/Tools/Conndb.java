package Tools;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class Conndb {
	/*
	 * 
	 * */
	public Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";//
	    //String url = "jdbc:mysql://localhost:2333/huanuo";
	    String url = "jdbc:mysql://172.16.201.17:3306/huanuo?characterEncoding=utf8&useSSL=true";//
	    String username = "root";//
	   // String password = "root";//
	    //172.16.201.17
	   String password = "newpassword";
	    Connection conn = null;//
	    try {
	        Class.forName(driver); //
	        conn = (Connection) DriverManager.getConnection(url, username, password);//
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
}
