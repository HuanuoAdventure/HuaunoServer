package DataMid;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.DocumentCollection;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.View;
import net.sf.json.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import JavaBean.InformBean;


/**
 * 2017/6/22
 * @author duxiaohan
 * TODO 从Domino中获取一个人的信息 返回一个Json
 */
public class DominoInform {
	Logger logger=LogManager.getLogger(DominoInform.class);//获取Logger信息
	public InformBean informBean;
	public JSONObject getOneInform(String search,String tag)//获取个人信息的JsonObject，第一个参数为需要搜索的内容，第二个参数为第一个参数的类型"id" or "name"
	{
		JSONObject jsonObject=null;
		String host="192.168.0.38:63148";//定义数据库地址
		Session ss=null;//定义session
		Database db=null;//定义数据库
		View view01=null;//定义视图01
		View view02=null;//定义视图02
		//连接数据库
		try {
			ss=NotesFactory.createSession(host,"admin","123456");
			//System.out.println("Session created");
			logger.info("Session created");
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\txl.nsf");//第一个参数为服务器名称，第二个参数是对应数据库名称，
			logger.info("Got Database");											    //只有在Domino服务器上第一个参数才能为空
			//System.out.println("Got Database");
			view01=db.getView("AView01");//获取别名为AView01的视图名称，此视图为按姓名筛选的通讯录
			//System.out.println("Got View");
			logger.info("Got AView01");
			view02=db.getView("AView02");//获取别名为Aview02的视图名称，此视图为工号-姓名
			logger.info("Got AView02");
			if(tag.equals("id"))
			{//如果参数为ID则首先要通过Aview02找到姓名
				Document doc=view02.getDocumentByKey(search,true);
				if (doc !=null){
					String sname=(String) doc.getItemValue("Name").get(0);
					Document document=view01.getDocumentByKey(sname,true);
					//getItemValue获取的是向量Vector类型
					if(document !=null){
					String name=(String) document.getItemValue("Name").get(0);//姓名
					String id=(String) document.getItemValue("NO").get(0);//工号
					String depart=(String) document.getItemValue("Department").get(0);//部门
					String email=(String) document.getItemValue("EMail").get(0);//邮箱
					String tel=(String) document.getItemValue("MP").get(0);//手机
					String gender="暂无";
					String sex=String.valueOf(document.getItemValue("Sex").get(0));//从数据中取出的性别字段1/2，1.0/2.0
					if(sex.equals("2")||sex.equals("2.0"))
					{
						gender="女";
					}
					else if(sex.equals("1")||sex.equals("1.0")) {
						gender="男";
					}
					String iconurl="http://oa.huanuo-nsb.com/photo/"+(String) document.getItemValue("id").get(0)+".jpg";//照片ID
					//http://oa.huanuo-nsb.com/photo/1209.jpg 图片的实际地址
					jsonObject=new JSONObject();
					jsonObject.put("iconurl", iconurl);
					jsonObject.put("name",name);
					jsonObject.put("id", id);
					jsonObject.put("tel", tel);
					jsonObject.put("depart", depart);
					jsonObject.put("email", email);
					jsonObject.put("gender", gender);//暂无字段
					jsonObject.put("location", "暂无");//暂无字段
					}
				}
			}
			else if(tag.equals("name"))
			{//如果参数为姓名，则可以直接搜索
				Document document=view01.getDocumentByKey(search,true);
				//getItemValue获取的是向量Vector类型
				if(document !=null){
				String name=(String) document.getItemValue("Name").get(0);//姓名
				String id=(String) document.getItemValue("NO").get(0);//工号
				String depart=(String) document.getItemValue("Department").get(0);//部门
				String email=(String) document.getItemValue("EMail").get(0);//邮箱
				String tel=(String) document.getItemValue("MP").get(0);//手机
				String gender="暂无";
				String sex=String.valueOf(document.getItemValue("Sex").get(0));//从数据中取出的性别字段1/2，1.0/2.0
				if(sex.equals("2")||sex.equals("2.0"))
				{
					gender="女";
				}
				else if(sex.equals("1")||sex.equals("1.0")) {
					gender="男";
				}
				String iconurl="http://oa.huanuo-nsb.com/photo/"+(String) document.getItemValue("id").get(0)+".jpg";//照片ID
				//http://oa.huanuo-nokia.com/photo/1209.jpg 图片的实际地址
				jsonObject=new JSONObject();
				jsonObject.put("iconurl", iconurl);
				jsonObject.put("name",name);
				jsonObject.put("id", id);
				jsonObject.put("tel", tel);
				jsonObject.put("depart", depart);
				jsonObject.put("email", email);
				jsonObject.put("gender", gender);//暂无字段
				jsonObject.put("location", "暂无");//暂无字段
				}
			}
			//System.out.println(view01.getAllEntries().getCount());//获取当前视图可筛选的列数
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				view01.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn("view01 recycle failed");
			}
			try {
				view02.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn("view02 recycle failed");
			}
			try {
				db.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn("database recycle failed");
			}
			try {
				ss.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn("session recycle failed");
			}
			
		}
		return jsonObject;
	}
	public JSONObject getInformList(String str,String tag)
	{//返回人名列表
		JSONObject jsonObject=null;
		String host="192.168.0.38:63148";//定义数据库地址
		Session ss=null;//定义session
		Database db=null;//定义数据库
		View view01=null;//定义视图01
		View view02=null;//定义视图02
		try {
			ss=NotesFactory.createSession(host,"admin","123456");
			//System.out.println("Session created");
			logger.info("Session created");
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\txl.nsf");//第一个参数为服务器名称，第二个参数是对应数据库名称，
			logger.info("Got Database");											    //只有在Domino服务器上第一个参数才能为空
			//System.out.println("Got Database");
			
			if(tag.equals("depart")){//部门人员列表
				view01=db.getView("HView03");//获取别名为HView03的视图名称，此视图为按部门筛选的通讯录
				//System.out.println("Got View");
				logger.info("Got HView03");
				DocumentCollection dc =view01.getAllDocumentsByKey(str,true);//true表示完全匹配
				//System.out.println(dc.getCount());
				logger.info("Got documents number:"+dc.getCount());
				if (dc !=null) {
					logger.info("dc is not null");
					Document doc=dc.getFirstDocument();
					Document tmpDoc=null;
					if(doc !=null){
						jsonObject=new JSONObject();
						jsonObject.put("rowCount", (int)dc.getCount());
						int rowCount=0;
						while(doc!=null)//判断当前doc是否为空
						{
							//System.out.println(doc.getItemValue("Name"));
							rowCount++;
							jsonObject.put(String.valueOf(rowCount), doc.getItemValue("Name").get(0));
							tmpDoc=dc.getNextDocument(doc);//如果第一个文件不为空，则获取第二个文件
							//doc.recycle();//回收第一个文件
							doc=tmpDoc;//将第二个文件赋值给第一个文件
						}
					}
					dc.recycle();
					logger.info("dc recycled");
					view01.recycle();
					logger.info("view01 recycled");
				}
				
			}
			else if(tag.equals("str"))//搜索框响应人员列表
			{
				if (str.matches("[0-9]{1,}"))
				{
					view02=db.getView("AView03");//获取别名为Aview01的视图名称，此视图按姓名筛选的通讯录
					logger.info("Got AView03");
					DocumentCollection dc =view02.getAllDocumentsByKey(str,false);//false表示半匹配
					logger.info("Got documents number:"+dc.getCount());
					if (dc !=null) {
						logger.info("dc is not null");
						System.out.println("dc is not null");
						Document doc=dc.getFirstDocument();
						Document tmpDoc=null;
						if(doc !=null){
							jsonObject=new JSONObject();
							int docCount=dc.getCount();
							if(docCount>50)
							{
								jsonObject.put("more", 1);
								jsonObject.put("rowCount", 50);
							}else
							{
								jsonObject.put("more", 0);
								jsonObject.put("rowCount", (int)dc.getCount());
							}
							
							int rowCount=0;
							while((doc!=null)&&(rowCount<50))//判断当前doc是否为空,且是否超过50个
							{
								//System.out.println(doc.getItemValue("Name"));
								rowCount++;
								jsonObject.put(String.valueOf(rowCount), doc.getItemValue("NO").get(0));
								tmpDoc=dc.getNextDocument(doc);//如果第一个文件不为空，则获取第二个文件
								//doc.recycle();//回收第一个文件
								doc=tmpDoc;//将第二个文件赋值给第一个文件
							}
						}
						dc.recycle();
						logger.info("dc recycled");
						view02.recycle();
						logger.info("view02 recycled");
					}
				}
					else {view02=db.getView("AView01");//获取别名为Aview01的视图名称，此视图按姓名筛选的通讯录
					logger.info("Got AView01");
					DocumentCollection dc =view02.getAllDocumentsByKey(str,false);//false表示半匹配
					logger.info("Got documents number:"+dc.getCount());
					if (dc !=null) {
						logger.info("dc is not null");
						System.out.println("dc is not null");
						Document doc=dc.getFirstDocument();
						Document tmpDoc=null;
						if(doc !=null){
							jsonObject=new JSONObject();
							int docCount=dc.getCount();
							if(docCount>50)
							{
								jsonObject.put("more", 1);
								jsonObject.put("rowCount", 50);
							}else
							{
								jsonObject.put("more", 0);
								jsonObject.put("rowCount", (int)dc.getCount());
							}
							
							int rowCount=0;
							while((doc!=null)&&(rowCount<50))//判断当前doc是否为空,且是否超过50个
							{
								//System.out.println(doc.getItemValue("Name"));
								rowCount++;
								jsonObject.put(String.valueOf(rowCount), doc.getItemValue("Name").get(0));
								tmpDoc=dc.getNextDocument(doc);//如果第一个文件不为空，则获取第二个文件
								//doc.recycle();//回收第一个文件
								doc=tmpDoc;//将第二个文件赋值给第一个文件
							}
						}
						dc.recycle();
						logger.info("dc recycled");
						view02.recycle();
						logger.info("view02 recycled");
					}
					}
				}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			try {
				db.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn("database recycle failed");
			}
			try {
				ss.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.warn("session recycle failed");
			}
		}
		
		return jsonObject;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
