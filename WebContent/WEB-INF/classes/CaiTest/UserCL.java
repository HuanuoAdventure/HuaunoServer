package CaiTest;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Tools.Conndb;
public class UserCL {
	Connection conn=null;
	PreparedStatement pstate=null;
	Statement state=null;
	//Statement jg=null;
	public boolean addUser(int uid,String uname,String passwd,String rname,String locked){
		  int jg=0;
		  try{
		conn=(Connection)new Conndb().getConn();
		   pstate=(PreparedStatement)conn.prepareStatement("insert into users(uname,locked) values (?,?)");
		   pstate.setString(1, uname);
		   pstate.setString(2, locked);
		   jg=pstate.executeUpdate();
		  }catch(SQLException e){
		   e.printStackTrace();
		  }finally{
		   try{
		    pstate.close();
		   }catch(SQLException e){
		    e.printStackTrace();
		   }
		  }
		  if(jg>0)
		   return true;
		  else
		   return false;
		 }
		 
		 public boolean updateUser(String uname ){
		 int jg=0;
		  try{
		   conn=(Connection)new Conndb().getConn();
		   pstate=(PreparedStatement)conn.prepareStatement("update users set locked='YES' where uname='"+uname+"'");
		   //pstate.setString(1, uname);
		   jg=pstate.executeUpdate();//报错行
		  }catch(SQLException e){
		   e.printStackTrace();
		  }
		  finally{
			   try{ 
				    pstate.close();
				   }catch(SQLException e){
				    e.printStackTrace();
				   }
				  }
		  if(jg>0)
				   return true;
				  else
				   return false;
				 }
		 public boolean UpdateTime(String uname ){
			  int jg=0;
			  try{
			 conn=(Connection)new Conndb().getConn();				  
			   pstate=conn.prepareStatement("update users set locked_time=sysdate() where uname='"+uname+"'");
			   //pstate.setString(1, uname);
			   jg=pstate.executeUpdate();
			  }catch(SQLException e){
			   e.printStackTrace();
			  }finally{
			   try{
			    pstate.close();
			   }catch(SQLException e){
			    e.printStackTrace();
			   }
			  }
			  if(jg>0)
			   return true;
			  else
			   return false;
			 }
		 public boolean delUser(String name){
		  int jg=0;
		  try{
		   conn=(Connection)new Conndb().getConn();
		   state=(Statement) conn.createStatement();
		   jg=((java.sql.Statement) state).executeUpdate("delete from users where name='"+name+"'");
		  }catch(SQLException e){
		   e.printStackTrace();
		  }finally{
		   try{
		    ((Connection) state).close();
		   }catch(SQLException e){
		    e.printStackTrace();
		   }
		  }
		  if(jg>0)
		   return true;
		  else
		   return false;
		 }

}

