package com.three.globals;

import java.util.HashMap;
import java.util.Map;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.obj.BaseMessageJSON;

/**
 * ��Ϣ�ṩ��
 * @author JavaServer
 *
 */
public class MsgProvider {
	
	private Map<Integer,IMessage> msgMap = new HashMap<Integer,IMessage>();
	
	/**
	 * ��ȡ��Ϣ
	 * @param msgType
	 * @return
	 */
	public IMessage getByMsgType(int msgType){
		return msgMap.get(msgMap);
	}
	
	/**
	 * ������Ϣ
	 */
	public void putMsgType(BaseMessageJSON msg){
		msgMap.put(msg.getMsgType(), msg);
	}
}
