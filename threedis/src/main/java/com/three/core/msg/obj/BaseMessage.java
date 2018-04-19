package com.three.core.msg.obj;

import com.three.core.msg.inter.IMessage;

public abstract class BaseMessage implements IMessage{

	private int msgType;//消息的编号 NetMessageType 相当于消息的ID
	
	
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
