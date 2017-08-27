package DataMid;

import java.util.HashSet;
import java.util.Set;

import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.Session;
import lotus.domino.View;
import net.sf.json.JSONObject;

/**
 * 
 * @author duxiaohan
 * TODO 根据BinderDocIDOS获取Domino中的文档详细内容
 */
public class DominoDocument {
//根据DocID获取在办工作/待办工作/办结工作的详细文档情况
	public JSONObject getDetail(Session ss,String id,String docid,String worktype)//参数：数据库session，工号，文档ID，类型（待办工作/在办工作/完结工作）
	{
		JSONObject jsonObject=new JSONObject();
		Database db=null;
		View view=null;
		Document doc =null;
		String workView=null;
		if(worktype.equals("Ongoing"))
		{
			workView="Sview03";
		}else if (worktype.equals("ToDo"))
		{
			worktype="Sview01";
		}
		else if(worktype.equals("Finished"))
		{
			worktype="Sview04";
		}
		
		try {
			db=ss.getDatabase("oa.huanuo.com", "D:\\Lotus\\Domino\\data\\app\\yyk.nsf");
			//在办工作 Sview03
			
			view=db.getView("Sview03");
			doc =view.getFirstDocument();
			//Document tmpDoc=null;
			//int i=0;
			//Set<String> workListset=new HashSet<String>();
			boolean flag=false;//是否查找到所需文档的标识符
			while(doc!=null)//说明在办工作列表不为空
			{
				if(doc.getItemValue("BinderDocIDOS").toString().equals(docid))//如果docid为要查找的docid,获取doc中的信息存入JsonObject
				{
					flag=true;
					jsonObject.put("BinderDocIDOS", doc.getItemValue("BinderDocIDOS").toString());//docid
					jsonObject.put("No", doc.getItemValue("flowNo").toString());////No(右上角)	flowNo
					jsonObject.put("申请类型", doc.getItemValue("sqlx").toString());//申请类型	sqlx
					jsonObject.put("差旅类型", doc.getItemValue("cllb").toString());//差旅类型	cllb
					jsonObject.put("Homebase所在地", doc.getItemValue("hb_addr").toString());//Homebase所在地	hb_addr
					jsonObject.put("居住地", doc.getItemValue("jz_addr").toString());//居住地	jz_addr
					jsonObject.put("差旅事由", doc.getItemValue("ly").toString());//差旅事由	ly
					jsonObject.put("是否订机票", doc.getItemValue("sfdp").toString());//是否订机票	sfdp
					jsonObject.put("差旅时间开始", doc.getItemValue("ksrq").toString());//差旅时间开始	ksrq
					jsonObject.put("差旅时间结束", doc.getItemValue("jsrq").toString());//差旅时间结束	jsrq
					jsonObject.put("累计天数", doc.getItemValue("ts").toString());//累计天数	ts
					jsonObject.put("原文档ID", doc.getItemValue("BinderDocIDOS").toString());//原文档ID	docid
					jsonObject.put("机票预定信息", doc.getItemValue("jpxqstr").toString());//机票预定信息	jpxqstr
					jsonObject.put("成本中心编号", doc.getItemValue("cbzxbm").toString());//成本中心编号	cbzxbm
					jsonObject.put("申请人", doc.getItemValue("sqr").toString());//申请人	sqr
					jsonObject.put("部门", doc.getItemValue("bm").toString());//部门	bm
					jsonObject.put("申请日期", doc.getItemValue("sqrq").toString());//申请日期	sqrq
					jsonObject.put("Group Manager", doc.getItemValue("yj1").toString());//Group Manager	yj1
					jsonObject.put("Stream Manager", doc.getItemValue("yj2").toString());//Stream Manager	yj2
					jsonObject.put("Department Manager", doc.getItemValue("yj3").toString());//Department Manager	yj3
					jsonObject.put("General Manager", doc.getItemValue("yj4").toString());//General Manager	yj4
					jsonObject.put("订票处理情况", doc.getItemValue("yj5").toString());//订票处理情况	yj5
				}
				if(flag==false)//如果当前document不是
					{doc=view.getNextDocument(doc);}//获取下一个Document
				else 
					{break;}
				//i++;
				///workListset.add(jsonObject.toString());
			}
			System.out.println("============================docJson==============================");
			System.out.println(jsonObject);
			//for(String s:workListset)
			//{System.out.println(s);}
			if(jsonObject.length()==0)
			{
				jsonObject.put("CODE", "-8");
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
		return jsonObject;
	}
}
