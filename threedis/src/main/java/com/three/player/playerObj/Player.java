package com.three.player.playerObj;

import com.three.core.msg.inter.IMessage;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;

public class Player {

	private NettyClientSession nettyClientSession;
	
	
	public void setNettyClientSession(NettyClientSession nettyClientSession){
		this.nettyClientSession = nettyClientSession;
	}
	
	public NettyClientSession  getNettyClientSession(){
		return this.nettyClientSession;
	}
	
	
	public void sendMessage(IMessage msg){
		nettyClientSession.getGlobals().getMessageRecognizer().getGcBlockingMsgService().putMsgIntoCache(msg);
	}
}
