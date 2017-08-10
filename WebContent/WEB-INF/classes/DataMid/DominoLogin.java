package DataMid;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.Session;

/**
 * 
 * @author duxiaohan
 * TODO Domino登录验证中间层
 */
public class DominoLogin {
public static boolean Login(String username,String password)
{
	boolean res=false;
	Session ss=null;
	String host="192.168.0.38:63148";
	int flag=0;
	try {
		ss=NotesFactory.createSession(host,username,password);
	} catch (NotesException e) {
		// TODO Auto-generated catch block
		flag++;
		e.printStackTrace();
	}finally
	{
		if(flag==0)//说明没有进入catch 通过登录验证
		{
			res=true;
			try {
				ss.recycle();
			} catch (NotesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//回收会话
		}
	}
	
	return res;
}
}
