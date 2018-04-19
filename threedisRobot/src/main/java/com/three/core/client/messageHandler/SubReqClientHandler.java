package com.three.core.client.messageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.msg.transform.MsgTransform;
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
		ctx.write(subReq(1));
		ctx.flush();
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ctx.write(subReq(2));
		ctx.flush();
	}
	
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
			
		logger.info("channelRead0£∫"+ctx.channel().remoteAddress());
		
		SubcribeRespProto.SubcribeResp subcribeResp = (SubcribeRespProto.SubcribeResp)msg;
		
		subcribeResp.getSubRespID();
//		IMessage transMsg = MsgTransform.fromJSONString(subcribeResp.getJsonBody());
		logger.info(subcribeResp.getJsonBody());
		
		
	}
	
	
	
	

	private SubcribeReqProto.SubcribeReq subReq(int i){
		SubcribeReqProto.SubcribeReq.Builder builder = SubcribeReqProto.SubcribeReq.newBuilder();
		builder.setSubReqID(i);
		builder.setUserName("lilinfeng");
		builder.setProductName("Netty book for protobuf");
		builder.setAddress("street street");
		if(i ==1){
			builder.setJsonBody(MsgTransform.toJSONString(new CGLoginIn()));
		}else if(i == 2){
			builder.setJsonBody(MsgTransform.toJSONString(new CGSendGift()));
		}
		return builder.build();
		
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
