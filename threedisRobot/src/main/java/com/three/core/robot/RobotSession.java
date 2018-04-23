package com.three.core.robot;

import io.netty.channel.ChannelHandlerContext;

import com.three.core.msg.inter.IMessage;
import com.three.core.protobuf.ProtobufRobotTransform;

/**
 * »úÆ÷ÈËµÄsession
 * @author JavaServer
 *
 */
public class RobotSession {

	private ChannelHandlerContext ctx = null;
	
	public RobotSession(ChannelHandlerContext ctx){
		this.ctx = ctx; 
	}
	
	
	
	public void put(IMessage msg){
		ctx.writeAndFlush(ProtobufRobotTransform.toWriteMsg(msg));
	}
}
