package com.three.core.msg.obj;

import com.three.core.msg.inter.IMessage;

public abstract class BaseMessage implements IMessage{

	private int msgType;//��Ϣ�ı�� NetMessageType �൱����Ϣ��ID
	
	
	protected BaseMessage(int msgType){
		this.msgType=msgType;
	}
	
	
	public int getMsgType(){
		return this.msgType;
	}
	
	
	@Override
	public void execute() {
		
	}
}
