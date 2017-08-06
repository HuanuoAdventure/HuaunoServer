package Tools;

import java.util.Date;





import java.util.HashMap;

import javax.servlet.ServletContext;

import lotus.domino.Session;



public class Token {
	private String id="";
	private String pw="";
	
	public Token(String uname,String pwd)
	{
		id=uname;
		pw=pwd;
	}
	public String getToken()
	{
		//TODO:工号+当前系统时间生成token
		String token="";
		/*Date date = new Date();
		String midtoken=id+String.valueOf(date.getTime());
		double intToken=Double.valueOf(midtoken)*Double.valueOf(id);
		token=String.valueOf(intToken);*/
		Date date = new Date();
		String midtoken=id+"-"+pw+"-"+String.valueOf(date.getTime());
		try {
			DesUtils des = new DesUtils("token");//自定义密钥
			token=des.encrypt(midtoken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	public Boolean putToken(String token,ServletContext sc) {
		//JSONObject jsonObject=new JSONObject();
		//jsonObject.put("token", token);
		//jsonObject.put("LastReqTime", new Date().getTime());
		sc.setAttribute(id, token);
		HashMap<String, Long> LastReqTime= new HashMap<String,Long>();
		LastReqTime.remove(id);
		LastReqTime.put(id, new Date().getTime());
		sc.setAttribute("OnlineUsers", LastReqTime);
		return true;
	}
	public static String parseToken(String token)
	{
		String p="";
		try {
			DesUtils des = new DesUtils("token");//自定义密钥
			String p1=des.decrypt(token);
			String[] p2=p1.split("-");
			//DesUtils des2 = new DesUtils("leemenz");//自定义密钥
			//p=des2.decrypt(p2[1]);
			p=p2[1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
//	public static void main(String[] args) {
//		String id="800818";
//		String midtoken=id+String.valueOf(new Date().getTime());
//		Double intToken=Double.valueOf(midtoken)*Double.valueOf(id);
//		String token=String.valueOf(intToken);
//		System.out.println(token);
//		
//	}
}
