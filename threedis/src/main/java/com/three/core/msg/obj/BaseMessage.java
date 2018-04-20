package com.three.core.msg.obj;

import com.three.core.msg.inter.IMessage;
import com.three.core.session.NettyClientSession;
import com.three.player.playerObj.Player;

public abstract class BaseMessage implements IMessage{

	private int msgCode;//��Ϣ�ı�� NetMessageType �൱����Ϣ��ID
	private int msgType;//��Ϣ�ı�� NetMessageType �൱����Ϣ��ID
	private NettyClientSession nettyClientSession;
	
	protected BaseMessage(int msgCode,int msgType){
		this.msgCode=msgCode;
		this.msgType=msgType;
	}
	
	@Override
	public int getMsgCode(){
		return this.msgCode;
	}
	@Override
	public int getMsgType(){
		return this.msgType;
	}
	
	
	
	
	
	@Override
	public void setNettyClientSession(NettyClientSession nettyClientSession) {
		this.nettyClientSession = nettyClientSession;
	}
	@Override
	public NettyClientSession getNettyClientSession() {
		return this.nettyClientSession;
	}


	@Override
	public void execute() {
		
	}
}
