package com.three.core.client.messageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.msg.transform.MsgTransform;
import com.three.core.protobuf.ProtobufRobotTransform;
import com.three.core.protobuf.SubcribeReqProto;
import com.three.core.protobuf.SubcribeRespProto;
import com.three.gift.msg.CGSendGift;
import com.three.player.msg.CGLoginIn;

public class SubReqClientHandler extends SimpleChannelInboundHandler<Message>{
	
	Logger logger = Logger.getLogger(SubReqClientHandler.class);
	
	public SubReqClientHandler(){}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		logger.info("Œ“∏’…œœﬂ£∫"+ctx.channel().remoteAddress());
		ctx.writeAndFlush(ProtobufRobotTransform.toWriteMsg(new CGLoginIn()));
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ctx.writeAndFlush(ProtobufRobotTransform.toWriteMsg(new CGSendGift()));
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
