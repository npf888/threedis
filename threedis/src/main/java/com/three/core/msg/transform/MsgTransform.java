package com.three.core.msg.transform;

import com.alibaba.fastjson.JSON;
import com.three.core.msg.inter.IMessage;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.globals.Globals;

/**
 * ��Ϣת��
 * @author JavaServer
 *
 */
public class MsgTransform {

	
	
	/**
	 * ��Ϣת���ַ���
	 * @param msg
	 * @return
	 */
	public static String toJSONString(IMessage msg){
		return JSON.toJSONString(msg);
	}
	
	
	public static IMessage fromJSONString(String jsonMsg){
		BaseMessageJSON baseMessageJSON = JSON.parseObject(jsonMsg, BaseMessageJSON.class);
		return Globals.getMsgProvider().getByMsgType(baseMessageJSON.getMsgType());
	}
}
