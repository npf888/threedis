package com.three.core.msg.transform;

import com.alibaba.fastjson.JSON;
import com.three.core.msg.inter.IMessage;

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
	
	
	public static IMessage fromJSONString(String jsonMsg,IMessage message){
		IMessage subMessage = JSON.parseObject(jsonMsg,message.getClass());
		return subMessage;
	}
	


}
