package com.three.core.robot;

import io.netty.channel.ChannelHandlerContext;

import com.three.core.msg.inter.IMessage;

/**
 * �����˵�session
 * @author JavaServer
 *
 */
public class RobotSession {

	private ChannelHandlerContext ctx = null;
	
	public RobotSession(ChannelHandlerContext ctx){
		this.ctx = ctx; 
	}
	
	
	
	public void put(IMessage msg){
		ctx.writeAndFlush(msg);
	}
}
