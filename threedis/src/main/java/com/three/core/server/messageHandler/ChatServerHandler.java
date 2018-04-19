package com.three.core.server.messageHandler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.msg.transform.MsgTransform;
import com.three.core.protobuf.SubcribeReqProto;
import com.three.core.protobuf.SubcribeRespProto;
import com.three.gift.msg.CGSendGift;

public class ChatServerHandler extends SimpleChannelInboundHandler<Message> {  
		Logger logger = Logger.getLogger(ChatServerHandler.class);
		
	    @Override  
	    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)  
	        Channel incoming = ctx.channel();  
	        logger.info("ChatClient:" + incoming.remoteAddress() + "上线");  
	  
	    } 
	    
	    @Override  
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  
	        Channel incoming = ctx.channel();  
	  
	        logger.info("[SERVER] - " + incoming.remoteAddress() + " 离开\n");  
	  
	    }  
	  
	    @Override  
	    protected void channelRead0(ChannelHandlerContext ctx, Message msg)  
	            throws Exception {  
	    	SubcribeReqProto.SubcribeReq req = (SubcribeReqProto.SubcribeReq)msg;
	    	
	    	logger.info("来了。。。      。。。 ");
	    	logger.info("ID --------- "+req.getSubReqID());
	    	logger.info("Server accept client subcribe req: ["+req.toString()+"]");
			
				
			ctx.writeAndFlush(resp(req.getSubReqID()));
		}
		
	    
		public SubcribeRespProto.SubcribeResp resp(int subReqID){
			SubcribeRespProto.SubcribeResp.Builder builder = SubcribeRespProto.SubcribeResp.newBuilder();
			builder.setSubRespID(subReqID);
			builder.setRespCode(10);
			builder.setDesc("dddd");
			builder.setJsonBody(MsgTransform.toJSONString(new CGSendGift()));
			return builder.build();
		} 
	  
	    
		
	  
	    @Override  
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
	        Channel incoming = ctx.channel();  
	        // 当出现异常就关闭连接  
	        logger.info("ChatClient:" + incoming.remoteAddress()+ "异常,已被服务器关闭");  
	        cause.printStackTrace();  
	        ctx.close();  
	    }  
}
