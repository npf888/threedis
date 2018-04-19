package com.three.core.msg.inter;

import com.three.core.session.NettyClientSession;

public interface IMessage {

	void execute();
	
	
	void setNettyClientSession(NettyClientSession nettyClientSession);
	
	NettyClientSession getNettyClientSession();
	
}
