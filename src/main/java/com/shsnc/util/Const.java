package com.shsnc.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * 项目名称：
 * @author:fh
 * 
*/
public class Const {
	//营业厅渠道
	public static final String[] MSG1 = {"CRM-APP-G01", "CRM-APP-G02", "CRM-APP-G03", "CRM-APP-G04","CRM-APP-G36", "CRM-APP-G37", "CRM-APP-G38", "CRM-APP-G39"} ;
	public static final String OSB1 = "esb-ownchannel";
	//代理商渠道
	public static final String[] MSG2 ={"CRM-APP-G05", "CRM-APP-G06", "CRM-APP-G07", "CRM-APP-G08","CRM-APP-G40", "CRM-APP-G41", "CRM-APP-G42", "CRM-APP-G43"} ;
	public static final String OSB2 = "esb-socialchannel";
	//集团客户经理渠道
	public static final String[] MSG3 ={"CRM-APP-G05", "CRM-APP-G06", "CRM-APP-G07", "CRM-APP-G08"};
	public static final String OSB3 = "esb-directsales";
	//自助式语音渠道
	public static final String[] MSG4 ={"CRM-APP-G12", "CRM-APP-G13", "CRM-APP-G14", "CRM-APP-G15"};
	public static final String OSB4 = "esb-selfvoice";
	//互联网渠道
	public static final String[] MSG5 ={"CRM-APP-G12", "CRM-APP-G13", "CRM-APP-G14", "CRM-APP-G15"};
	public static final String OSB5 = "esb-selfservice";
	//短信渠道
	public static final String[] MSG6 ={"CRM-APP-G12", "CRM-APP-G13", "CRM-APP-G14", "CRM-APP-G15"};
	public static final String OSB6 = "esb-sms";
	//移动终端渠道
	public static final String[] MSG7 ={"CRM-APP-G17", "CRM-APP-G19", "CRM-APP-G24", "CRM-APP-G25", "CRM-APP-G26", "CRM-APP-G27", "CRM-APP-G28", "CRM-APP-G29", "CRM-APP-G30", "CRM-APP-G31", "CRM-APP-G32", "CRM-APP-G33", "CRM-APP-G34", "CRM-APP-G35"};
	public static final String OSB7 = "esb-app";
	//协助式语音渠道
	public static final String[] MSG8 ={"CRM-APP-G9", "CRM-APP-G10"};
	public static final String OSB8 = "esb-assistvoice";

	//公共的map
	public static Map<String,String> map(){
		Map<String,String> map = new TreeMap<>();
		map.put("msg1" , "营业厅渠道");
		map.put("msg2" , "代理商渠道");
		map.put("msg3" , "移动终端渠道");
		map.put("msg4" , "短信渠道");
		map.put("msg5" , "自助式语言渠道");
		map.put("msg6" , "互联网渠道");
		map.put("msg7" , "协助式渠道");
		map.put("msg8" , "集团客户渠道");
		return map;
	}

	//CRM的map集合
	public static Map<String , String[]> maprcrm(){
		Map<String,String[]> map = new TreeMap<>();
		map.put("msg1" , MSG1);
		map.put("msg2" , MSG2);
		map.put("msg3" , MSG3);
		map.put("msg4" , MSG4);
		map.put("msg5" , MSG5);
		map.put("msg6" , MSG6);
		map.put("msg7" , MSG7);
		map.put("msg8" , MSG8);
		return map;
	}
	//OSB的集合
	public static Map<String , String> maposb(){
		Map<String,String> map = new TreeMap<>();
		map.put("msg1" , OSB1);
		map.put("msg2" , OSB2);
		map.put("msg3" , OSB3);
		map.put("msg4" , OSB4);
		map.put("msg5" , OSB5);
		map.put("msg6" , OSB6);
		map.put("msg7" , OSB7);
		map.put("msg8" , OSB8);
		return map;
	}




}
