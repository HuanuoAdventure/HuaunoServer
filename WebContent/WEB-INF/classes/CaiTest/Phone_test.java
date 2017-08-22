package CaiTest;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.Item;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.View;
import lotus.domino.cso.EmbeddedObject;

import java.util.Vector;

import com.sun.xml.internal.ws.api.message.Attachment;


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
			
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\zswd.nsf");//第一个参数为服务器名称，第二个参数是对应数据库名称，	      										   								//只有在Domino服务器上第一个参数才能为空
			System.out.println("Got Database");
			System.out.println(db.getFileName());
			//view=db.getView("SView10");			
			view=db.getView("View01");//参数为通讯录的视图名称
			System.out.println("Got View");
			System.out.println(view.getAllEntries().getCount());//获取当前视图可筛选的列数
			//System.out.println(view.getAllEntries());
			Document doc=view.getFirstDocument();//获取第一个文件
			//System.out.println (doc.getItemValueString ("sjbm")); 
			/*Vector v = ss.evaluate("@AttachmentNames",doc);
			if (!v.isEmpty()) {
				for (int i = 0; i < v.size(); i++) {
					EmbeddedObject eObject=(EmbeddedObject) doc.getAttachment((String)v.elementAt(i));
					if (eObject!=null) {
					//Attachment fileInfo=new Attachment();
						StringBuffer urlBuffer=new StringBuffer();
						//urlBuffer.append(DocTest.getComfig("dominoFileDown"));
						urlBuffer.append("user_id").append("name");
						System.out.println(v);
						System.out.println(eObject);
						
					}
					
				}
				
				
			}*/
			
			while(doc!=null)//判断当前doc是否为空
			{
				System.out.println(doc.getColumnValues());
			    System.out.println (doc.getItemValue("unid")); 
				//System.out.println (doc.getItemValueString("id"));
				//System.out.println (view.getColumnNames());//获取列名
				System.out.println(doc.getItems());//获取域名
				//System.out.println(doc.getColumnValues());
				
			//	System.out.println (doc.getItemValue("drcl"));
				//System.out.println (doc.getItemValue("position"));
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
			System.out.println(doc.getItemValue("$FILE"));
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
