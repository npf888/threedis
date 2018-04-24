package com.three.core.msg.transform;

import com.alibaba.fastjson.JSON;
import com.three.core.msg.inter.IMessage;

/**
 * msg json 转换
 * @author JavaServer
 *
 */
public class MsgTransform {

	
	
	public static String toJSONString(IMessage msg){
		return JSON.toJSONString(msg);
	}
	
	
	public static IMessage fromJSONString(String jsonMsg,IMessage message){
		IMessage subMessage = JSON.parseObject(jsonMsg,message.getClass());
		return subMessage;
	}
	


}
