package test;

import java.util.Vector;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;
import net.sf.json.JSONObject;

import org.apache.log4j.PropertyConfigurator;

import DataMid.DominoLogin;
import DataMid.DominoWorkList;
import Tools.Token;

import com.sun.istack.internal.logging.Logger;

public class LogTest {

	Logger logger=Logger.getLogger(LogTest.class);
	public LogTest()
	{
		logger.info("Test Test Test....");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PropertyConfigurator.configure("../log4j.properties");
		//LogTest logTest=new LogTest();
		//boolean res=DominoLogin.Login("801186", "123456");
		//System.out.println(res);
		//String string=Token.parseToken("f3925d18d2f90a2736870ae402e92a3109d8c2b67731bfa6f53169901474cb5c");
		//System.out.println(string);
		Session ss=null;
		//Database db=null;
		//View view=null;
		try {
			ss=NotesFactory.createSession("192.168.0.38:63148","800985","123456");
			System.out.println("Got session");
			DominoWorkList dominoWorkList=new DominoWorkList();
			//System.out.println(dominoWorkList.getOngoingWorkList(ss, "800985"));
			System.out.println(dominoWorkList.getFinishedWorkList(ss, "800985"));
		} catch (NotesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//view.recycle();
				//db.recycle();
				ss.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
