package com.three.core.client.messageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.msg.transform.MsgTransform;
import com.three.core.protobuf.SubcribeReqProto;
import com.three.core.protobuf.SubcribeRespProto;
import com.three.gift.msg.CGSendGift;

public class SubReqClientHandler extends SimpleChannelInboundHandler<Message>{
	
	Logger logger = Logger.getLogger(SubReqClientHandler.class);
	
	public SubReqClientHandler(){}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		logger.info("Œ“∏’…œœﬂ£∫"+ctx.channel().remoteAddress());
		ctx.write(subReq(100000000));
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
		builder.setJsonBody(MsgTransform.toJSONString(new CGSendGift()));
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
