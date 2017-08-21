package CaiTest;

import net.sf.json.JSONObject;
import DataMid.DominoInform;


public class test {
	public static void main(String[] args) {

		//String tag="华诺";
		DominoInform2 aTest=new DominoInform2();
		JSONObject b=aTest.getDocList("生活在华诺","DocumentList");//报错行
		System.out.println(b);
    }
  }
/*public static void main(String[] args) {

	//String tag="华诺";
	DominoInform aTest=new DominoInform();
	JSONObject b=aTest.getAnnoInform("华诺新邮箱启用通知", "Anno");
	System.out.println(b);
}
}*/