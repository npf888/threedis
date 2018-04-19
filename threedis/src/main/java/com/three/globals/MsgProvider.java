package com.three.globals;

import java.util.HashMap;
import java.util.Map;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.obj.BaseMessageJSON;

/**
 * 消息提供器
 * @author JavaServer
 *
 */
public class MsgProvider {
	
	private Map<Integer,IMessage> msgMap = new HashMap<Integer,IMessage>();
	
	/**
	 * 获取消息
	 * @param msgType
	 * @return
	 */
	public IMessage getByMsgType(int msgType){
		return msgMap.get(msgMap);
	}
	
	/**
	 * 放置消息
	 */
	public void putMsgType(BaseMessageJSON msg){
		msgMap.put(msg.getMsgType(), msg);
	}
}
