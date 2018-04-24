package com.three.player.playerObj;

import com.three.core.msg.inter.IMessage;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;
import com.three.player.db.Human;

public class Player {

	private NettyClientSession nettyClientSession;
	
	private Human human;
	
	
	
	
	public Human getHuman() {
		return human;
	}
	public void setHuman(Human human) {
		this.human = human;
	}

	public void setNettyClientSession(NettyClientSession nettyClientSession){
		this.nettyClientSession = nettyClientSession;
	}
	
	public NettyClientSession  getNettyClientSession(){
		return this.nettyClientSession;
	}
	
	
	public void sendMessage(IMessage msg){
		Globals.getMessageRecognizer().getGcBlockingMsgService().putMsgIntoCache(msg);
	}
}
