package test;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import JavaBean.InformBean;
import Tools.Conndb;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.jmx.snmp.Timestamp;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			test1
			Conndb conndb=new Conndb();
			Connection con=conndb.getConn();
			InformBean informBean=new InformBean(con, "801254");
			InformBean informBean1=new InformBean(con, "800818");
			System.out.println(informBean.getDepart());
			JSONObject jsonObject=new JSONObject();
			jsonObject=informBean.getJson();
			System.out.println(jsonObject.toString());
			System.out.println(jsonObject.getString("name"));
			System.out.println(informBean1.getJson().toString());
			con.close();
			String string=jsonObject.toString();
			JSONObject js=JSONObject.fromString(string);
			JSONArray jsonArray=new JSONArray();
			jsonArray.put(jsonObject);
			jsonArray.put(informBean1.getJson());
			for(int i=0;i<jsonArray.length();i++)
			{
				System.out.println(jsonArray.get(i));
			}
			System.out.println(js);
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			DateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date=new Date();
			System.out.println(date.getTime());
			Date newDate=new Date(date.getTime());
			System.out.println(df.format(newDate).toString());
			
		
					} catch (Exception e) {
			
		}*/
	//	HashMap<String, Long> LastReqTime= new HashMap<String,Long>();
		//LastReqTime.remove("801254");
	//	LastReqTime.put("801254", (long) 1);
	//	LastReqTime.put("801255", (long) 2);
	//	LastReqTime.put("801256", (long) 3);
		  //for(HashMap.Entry<String, Long> entry:LastReqTime.entrySet())  
		    //{  
		     //   System.out.println(entry.getKey()+":"+entry.getValue());  
		   // }
		  boolean isNunicodeDigits=StringUtils.isNumeric("8");
		  System.out.println(isNunicodeDigits);
	}
	

}
