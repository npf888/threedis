package com.three.core.client.messageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.protobuf.ProtobufRobotTransform;
import com.three.core.robot.RobotSession;
import com.three.globals.RobotGlobals;

public class SubReqClientHandler extends SimpleChannelInboundHandler<Message>{
	
	Logger logger = Logger.getLogger(SubReqClientHandler.class);
	
	public SubReqClientHandler(){}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		logger.info("Œ“∏’…œœﬂ£∫"+ctx.channel().remoteAddress());
		
		RobotSession tRobotSession = new RobotSession(ctx);
		RobotGlobals.setRobotSession(tRobotSession);
		
	}
	
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
			
		ProtobufRobotTransform.toReadMsg(msg, ctx);
		
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx){
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}


}
