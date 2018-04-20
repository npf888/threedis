package com.three.core.msg.inter;

import com.three.core.session.NettyClientSession;

public interface IMessage {

	public static final int CG_MSG_TYPE=1;
	public static final int GC_MSG_TYPE=2;
	
	int getMsgCode();
	
	int getMsgType();
	
	void execute();
	
	void setNettyClientSession(NettyClientSession nettyClientSession);
	
	NettyClientSession getNettyClientSession();
	
}
