package com.three.core.msg.obj;

import com.three.core.msg.inter.IMessage;
import com.three.core.session.NettyClientSession;
import com.three.player.playerObj.Player;

public abstract class BaseMessage implements IMessage{

	private int msgType;//消息的编号 NetMessageType 相当于消息的ID
	private NettyClientSession nettyClientSession;
	
	protected BaseMessage(int msgType){
		this.msgType=msgType;
	}
	
	
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
