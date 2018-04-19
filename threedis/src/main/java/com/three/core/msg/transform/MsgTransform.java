package com.three.core.msg.transform;

import com.alibaba.fastjson.JSON;
import com.three.core.msg.inter.IMessage;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.globals.Globals;

/**
 * 消息转换
 * @author JavaServer
 *
 */
public class MsgTransform {

	
	
	/**
	 * 消息转成字符串
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
