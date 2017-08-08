package DataMid;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.DocumentCollection;
import lotus.domino.NotesException;
import lotus.domino.Session;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;
import net.sf.json.JSONObject;

public class DominoWorkList {
	Logger logger=LogManager.getLogger(DominoWorkList.class);
	//获取用户名
	public String getUsername(Session ss,String id)
	{
		String usernameString="";
		Database db=null;
		View view=null;
		try {
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\xtwh.nsf");
			view=db.getView("AView");
			Document document=view.getDocumentByKey(id, false);
			usernameString=document.getItemValue("yhmc").get(0).toString();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				view.recycle();
				db.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("recycle error");
			}
			
		}
		
		return usernameString;
	}
	//在办工作
	@SuppressWarnings("null")
	public JSONObject getOngoingWorkList(Session ss,String id)//获取在办工作列表
	{
		JSONObject jsonList=new JSONObject();
		Database db=null;
		View view=null;
		Document doc =null;
		try {
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\yyk.nsf");
			//在办工作 Sview03
			view=db.getView("Sview03");
			doc =view.getFirstDocument();
			//Document tmpDoc=null;
			//int i=0;
			Set<String> workListset=new HashSet<String>();
			while(doc!=null)//说明在办工作列表不为空,取出所有的待办工作
			{
				//获取doc中的信息存入JsonObject
				//i++;
				JSONObject jsonObject=new JSONObject();//
				jsonObject.put("BinderDocIDOS", doc.getItemValue("BinderDocIDOS").toString());//
				//jsonObject.put("ExpandedParticipantAuthorsOS", doc.getItemValue("ExpandedParticipantAuthorsOS").toString());
				jsonObject.put("PROCESSOS", doc.getItemValue("PROCESSOS").toString());
				jsonObject.put("ACTIVITYOS", doc.getItemValue("ACTIVITYOS").toString());
				jsonObject.put("sqr",doc.getItemValue("sqr").toString());
				//jsonObject.put("S_CurrentUser", doc.getItemValue("S_CurrentUser").toString());
				jsonObject.put("JobStartedOS", doc.getItemValue("JobStartedOS").toString());
				jsonObject.put("ry_dis",doc.getItemValue("ry_dis").toString());
				jsonObject.put("back",doc.getItemValue("back").toString());
				//System.out.println(jsonObject);
				//jsonList.put(String.valueOf(i), jsonObject);
				workListset.add(jsonObject.toString());
				doc=view.getNextDocument(doc);//获取下一个Document
			}
			System.out.println("============================Set==============================");
			//System.out.println(workListset);
			//for(String s:workListset)
			//{System.out.println(s);}
			//获取当前登录用户的名称
			String name=getUsername(ss,id);
			if (name == null){
				jsonList.put("CODE", "-6");
			}
			else if(workListset.size()==0)
			{
				jsonList.put("CODE", "-7");
			}
			else {
				jsonList=searchMyList(workListset,name);
			}	
				
			
			//System.out.println(name);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			try {
				//doc.recycle();
				view.recycle();
				db.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonList;
	}
//待办工作
	public JSONObject getTodoWorkList(Session ss,String id)
	{
		//JSONObject jsonObject=null;
		JSONObject jsonList=new JSONObject();
		String host="192.168.0.38:63148";
		Database db=null;
		View view=null;
		try {
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\yyk.nsf");																			//只有在Domino服务器上第一个参数才能为空
			System.out.println("Got Database");
			System.out.println(db.getFileName());
			view=db.getView("Sview01");//待办工作（首页待办）
			System.out.println("Got View");////在办差旅申请 独占验证
			Set<String> workListset=new HashSet<String>();
			System.out.println(view.getColumnNames());
			ViewEntryCollection veCollection=view.getAllEntries();
			ViewEntry vEntry=veCollection.getFirstEntry();
			String name=getUsername(ss,id);//"常琦伟";
			int i=0;
			while (vEntry!=null) {
				//System.out.println(vEntry.getColumnValues());
				//workListset.add((String[]) vEntry.getColumnValues().toArray());
				//System.out.println(vEntry.getColumnValues().toString());
				String string=vEntry.getColumnValues().toString();
				String string2=string.substring(1, string.length()-1);
				String[] strings=string2.split(",");
				if(strings[0].equals("CN="+name+"/O=HUANUO-NSN"))
				{
					i++;
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("PREVIEW", strings[1]);
					jsonObject.put("BinderDocIDOS", strings[2]);
					jsonList.put(String.valueOf(i),jsonObject);
				}
				
				//System.out.println(strings);
				//JSONObject jsonObject=new JSONObject();						
				vEntry=veCollection.getNextEntry(vEntry);
			}
			//System.out.println(jsonList);
			jsonList.put("rowCount", String.valueOf(i));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonList;
	}
//办结工作
	public JSONObject getFinishedWorkList(Session ss,String id)
	{
		JSONObject jsonList=new JSONObject();
		Database db=null;
		View view=null;
		Document doc =null;
		try {
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\yyk.nsf");
			//在办工作 Sview03
			view=db.getView("Sview04");
			doc =view.getFirstDocument();
			//Document tmpDoc=null;
			//int i=0;
			Set<String> workListset=new HashSet<String>();
			while(doc!=null)//说明在办工作列表不为空,取出所有的待办工作
			{
				//获取doc中的信息存入JsonObject
				//i++;
				JSONObject jsonObject=new JSONObject();//
				jsonObject.put("BinderDocIDOS", doc.getItemValue("BinderDocIDOS").toString());//文档ID
				//jsonObject.put("ExpandedParticipantAuthorsOS", doc.getItemValue("ExpandedParticipantAuthorsOS").toString());
				jsonObject.put("PROCESSOS", doc.getItemValue("PROCESSOS").toString());//申请类型
				//jsonObject.put("ACTIVITYOS", doc.getItemValue("ACTIVITYOS").toString());
				jsonObject.put("sqr",doc.getItemValue("sqr").toString());//申请人
				jsonObject.put("JobStartedOS", doc.getItemValue("JobStartedOS").toString());//申请日期
				jsonObject.put("PREVIOUSACTIVITYOS",doc.getItemValue("PREVIOUSACTIVITYOS").toString());//归档状态
				jsonObject.put("UpdatedBy", doc.getItemValue("$UpdatedBy").toString());//参与人
				//System.out.println(jsonObject);
				//jsonList.put(String.valueOf(i), jsonObject);
				workListset.add(jsonObject.toString());
				doc=view.getNextDocument(doc);//获取下一个Document
			}
			System.out.println("============================Set==============================");
			//System.out.println(workListset);
			//for(String s:workListset)
			//{System.out.println(s);}
			//获取当前登录用户的名称
			String name=getUsername(ss,id);
			if (name == null){
				jsonList.put("CODE", "-6");
			}
			else if(workListset.size()==0)
			{
				jsonList.put("CODE", "-7");
			}
			else {
				jsonList=searchMyFinishedList(workListset,name);
			}	
				
			
			//System.out.println(name);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			try {
				//doc.recycle();
				view.recycle();
				db.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return jsonList;
	}
	public JSONObject searchMyFinishedList(Set<String> workListset,String username)
	{
		JSONObject jsonList=new JSONObject();
		
		username="CN="+username+"/O=HUANUO-NSN";
		System.out.println(username);
		int i=0;
		for(String s:workListset)
		{
			//System.out.println(s);
			JSONObject jsonObject=JSONObject.fromString(s);//获取单据对象 
			//System.out.println(jsonObject);
			//比较字段
			String UpdatedBy=jsonObject.getString("UpdatedBy");//$UpdatedBy:[CN=蔡如男/O=HUANUO-NSN, CN=曹广路/O=HUANUO-NSN, CN=常琦伟/O=HUANUO-NSN, CN=admin/O=HUANUO-NSN]
			//System.out.println(UpdatedBy);
			//分割字符串
			String UpdatedBy1=UpdatedBy.substring(1, UpdatedBy.length()-1);
			//System.out.println(UpdatedBy1);
			String[] users=UpdatedBy1.split(", ");
			//System.out.println("username"+username);
			//System.out.println("sqr="+sqr);
			//System.out.println("ry_dis="+ry_dis);
			//System.out.println("back="+back);
			boolean flag=false;
			//看参与人里是否有自己
			for(String s1:users)
			{
				//System.out.println(s1);
				if (s1.equals(username)) {
					flag=true;
				}
			}
			//System.out.println(flag);
			if(flag==true)//如果有与自己相关的申请单，则将申请单信息返回给客户端
			{
				i++;
				//System.out.println(i);
				JSONObject jsonObject2=jsonObject;
				jsonObject2.remove("UpdatedBy");
				jsonList.put(String.valueOf(i), jsonObject2);
			}
			}
		jsonList.put("rowCount", String.valueOf(i));
		//System.out.println(jsonList);
		return jsonList;
	}
	public JSONObject searchMyList(Set<String> workListset,String username) {
		// TODO 
		JSONObject jsonList=new JSONObject();
		username="["+username+"]";
		int i=0;
		for(String s:workListset)
		{
			//System.out.println(s);
			JSONObject jsonObject=JSONObject.fromString(s);//获取单据对象 
			//System.out.println(jsonObject);
			//比较字段
			String sqr=jsonObject.getString("sqr");
			String ry_dis=jsonObject.getString("ry_dis");
			String back=jsonObject.getString("back");
			//System.out.println("username"+username);
			//System.out.println("sqr="+sqr);
			//System.out.println("ry_dis="+ry_dis);
			//System.out.println("back="+back);
			if(username.equals(sqr)||username.equals(ry_dis)||username.equals(back))//如果有与自己相关的申请单，则将申请单信息返回给客户端
			{
				i++;
				//System.out.println(i);
				JSONObject jsonObject2=jsonObject;
				jsonObject2.remove("back");
				jsonList.put(String.valueOf(i), jsonObject2);
			}
			}
		jsonList.put("rowCount", String.valueOf(i));
		return jsonList;
	}
}
