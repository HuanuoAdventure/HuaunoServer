package CaiTest;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.DocumentCollection;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;

public class DominoTest2 {
	public void ReadDomino() {
		
		//Domino Server 主机名或者IP
	    String host = "192.168.0.38:63148";
		//登录用户名
	  String user = "admin";
	  // 登录用户密码
	 String password = "123456";		
			try {
				// 第一个参数由ip:port 组成
				Session ss = NotesFactory.createSession(host, user,password);
				Database db = ss.getDatabase(null, "D:\\Lotus\\Domino\\data\\app\\txl.nsf");
				System.out.println("All forms : " + db.getForms());// 取得所有表单
				System.out.println("All views : " + db.getViews());// 取得所有视图
				System.out.println("All documents : " + db.getAllDocuments());// 所有document 的 DocumentCollection
				DocumentCollection dc = db.getAllDocuments();
				System.out.println("All documents count : " + dc.getCount());
				// 遍历该database 的DocumentCollection
				for (int i = 1; i <= dc.getCount(); i++) {
					Document doc = dc.getNthDocument(i);
					System.out.println("document " + i + " : " + doc);
					// document 的 XMl数据
					System.out.println("document " + i + " XML : "
							+ doc.generateXML());
					// 该document 的 HTTPURL地址
					System.out.println("document " + i + " HTTPURL : "
							+ doc.getHttpURL());
					System.out.println("document " + i + " NotesURL : "
							+ doc.getNotesURL());
				}
				System.out.println("Categories : " + db.getCategories());
				System.out.println("form : " + db.getForm("form1"));
				System.out.println("view : " + db.getView("vabc"));

				ss.recycle();
			} catch (NotesException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public static void main(String[] args) {
		DominoTest2 t1 = new DominoTest2();
		t1.ReadDomino();
	}

}


