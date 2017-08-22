package CaiTest;

import net.sf.json.JSONObject;
import DataMid.DominoInform;


public class test {

public static void main(String[] args) {

	//String tag="华诺";
	DominoInform2 aTest=new DominoInform2();
	JSONObject b=aTest.getDocment("员工体检须知", "Doc");
	System.out.println(b);
}
}