package CaiTest;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.Item;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.View;

import java.util.Vector;


public class Phone_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host="192.168.0.38:63148";
		Session ss=null;
		Database db=null;
		View view=null;
		Document tmpDoc=null;
		try {
			//ss=NotesFactory.createSession(host);
			System.out.println(".......");
			ss=NotesFactory.createSession(host,"admin","123456");
			System.out.println("Session created");
			
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\tzgg.nsf");//第一个参数为服务器名称，第二个参数是对应数据库名称，	      										   								//只有在Domino服务器上第一个参数才能为空
			System.out.println("Got Database");
			System.out.println(db.getFileName());
			//view=db.getView("SView10");			
			view=db.getView("SView01");//参数为通讯录的视图名称
			System.out.println("Got View");
			System.out.println(view.getAllEntries().getCount());//获取当前视图可筛选的列数
			Document doc=view.getFirstDocument();//获取第一个文件
			//System.out.println (doc.getItemValueString ("sjbm")); 
			
			while(doc!=null)//判断当前doc是否为空
			{
				//System.out.println(doc.getColumnValues());
			    //System.out.println (doc.getItemValue("id")); 
				//System.out.println (doc.getItemValueString("id"));
				//System.out.println (view.getColumnNames());//获取列名
				System.out.println(doc.getItems());//获取域名
				System.out.println(doc.getColumnValues());
				System.out.println (doc.getItemValue("BinderDocIDOS"));
				System.out.println (doc.getItemValue("MAINDOCOS"));
				System.out.println (doc.getItemValue("ACTIVITYIDOS"));
				System.out.println (doc.getItemValue("FolderIDOS"));
				System.out.println (doc.getItemValue("FOLDERSTATUSOS"));
				System.out.println (doc.getItemValue("INSTANCEOS"));
				System.out.println (doc.getItemValue("INSTANCEIDOS"));
				System.out.println (doc.getItemValue("ACTIVITYOS"));
				System.out.println (doc.getItemValue("List"));
				System.out.println (doc.getItemValue("WebViewerURLOS"));
				System.out.println (doc.getItemValue("BinderDocIDOS"));
				System.out.println (doc.getItemValue("MAINDOCOS"));
				System.out.println (doc.getItemValue("ACTIVITYIDOS"));
				System.out.println (doc.getItemValue("FolderIDOS"));
				System.out.println (doc.getItemValue("FOLDERSTATUSOS"));
				System.out.println (doc.getItemValue("INSTANCEOS"));
				System.out.println (doc.getItemValue("INSTANCEIDOS"));
				System.out.println (doc.getItemValue("ACTIVITYOS"));
				System.out.println (doc.getItemValue("List"));
				System.out.println (doc.getItemValue("WebViewerURLOS"));
				
				
				//Item item = doc.getFirstItem("textItem");
				//System.out.println(item.getName());
				//System.out.println(item.getValueString());
				//Item item = doc.getFirstItem("numberItem");
				//System.out.println(item.getName());
				//System.out.println("Integer value: " + 
				//item.getValueInteger());
				//System.out.println("Double value: " + 
				//item.getValueDouble());
				//System.out.println(doc.getHttpURL());
				//System.out.println("HTTP:"+ view.getHttpURL());
				//System.out.println(view.setSelectionFormula("@Now < @Adjust(@modified; 0; 0; 6; 0; 0; 0)"));
				//System.out.println(view.getUniversalID());//获取view唯一标识
				//System.out.println(doc.getNotesURL());
				//System.out.println("http://oa.huanuo-nsb.com/app/zswd.nsf/SView01/"+doc.getHttpURL());
				//String s =(String)doc.getHttpURL();   
			       //String a[] = s.split("/");  //按照/截取字符串
		      // System.out.println(a[0]);  
			   // System.out.println(a[5]);   
				//String uRLString=
				//System.out.print("http://oa.huanuo-nsb.com/app/tzgg.nsf/SView01/"+a[5]);
				//System.out.println("Key: " + doc.getKey());
				tmpDoc=view.getNextDocument(doc);//如果第一个文件不为空，则获取第二个文件
				doc.recycle();//回收第一个文件
				doc=tmpDoc;//将第二个文件赋值给第一个文件
			}
			//System.out.println(doc.getItemValue("Name"));
			//[FORM, NO, Name, Department, xh, bmid, EMail, MP, id, photo_bz, Birthday, Sex, STATUS, PYSZM, $UpdatedBy, $Revisions]
		} catch (NotesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				//tmpDoc.recycle();
				//view.recycle();
				db.recycle();
				ss.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}
