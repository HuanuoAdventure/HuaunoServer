package CaiTest;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.View;



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
			
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\xtwh.nsf");//第一个参数为服务器名称，第二个参数是对应数据库名称，
	      										   								//只有在Domino服务器上第一个参数才能为空
			System.out.println("Got Database");
			System.out.println(db.getFileName());
			view=db.getView("DView03");
			//view=db.getView("ZView01");//参数为通讯录的视图名称
			System.out.println("Got View");
			System.out.println(view.getAllEntries().getCount());//获取当前视图可筛选的列数
			Document doc=view.getFirstDocument();//获取第一个文件
			//System.out.println (doc.getItemValueString ("sjbm")); 
			
			while(doc!=null)//判断当前doc是否为空
			{
				//System.out.println(doc.getColumnValues());
				System.out.println (doc.getItemValue("bmmc")); 
				System.out.println(doc.getItems());//获取域名
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
