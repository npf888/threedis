package com.three.core.server.messageHandler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.msg.transform.MsgTransform;
import com.three.core.protobuf.SubcribeReqProto;
import com.three.core.protobuf.SubcribeRespProto;
import com.three.core.session.NettyClientSession;
import com.three.gift.msg.CGSendGift;
import com.three.globals.Globals;

public class ChatServerHandler extends SimpleChannelInboundHandler<Message> {  
		Logger logger = Logger.getLogger(ChatServerHandler.class);
		
		
		
	    @Override  
	    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)  
	        Channel incoming = ctx.channel();  
	        logger.info("�û�:" + incoming.remoteAddress() + "����");  
	        NettyClientSession clientSession = new NettyClientSession(ctx);
	        Globals.setNettyClientSessionMap(clientSession);
	    } 
	    
	    @Override  
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  
	        Channel incoming = ctx.channel();  
	        logger.info("�û�:" + incoming.remoteAddress() + " �뿪\n");  
	        Globals.removeNettyClientSessionMap(incoming.remoteAddress().toString());
	    }  
	  
	    @Override  
	    protected void channelRead0(ChannelHandlerContext ctx, Message msg)  
	            throws Exception {  
	    	SubcribeReqProto.SubcribeReq req = (SubcribeReqProto.SubcribeReq)msg;
	    	
	    	
	    	String jsonBody = req.getJsonBody();
	    	if(StringUtils.isEmpty(jsonBody)){
	    		logger.info("��ǰ��ϢΪ�գ���Ϣ reqID:"+req.getSubReqID()+" --- ��Ϣ��:"+req.getJsonBody());
	    		return;
	    	}
	    	logger.info("��ǰ��Ϣ reqID:"+req.getSubReqID()+" --- ��Ϣ��:"+req.getJsonBody());
	    	NettyClientSession nettyClientSession = Globals.getNettyClientSessionMap(ctx.channel().remoteAddress().toString());
	    	Globals.getMessageRecognizer().recognize(req.getSubReqID(),jsonBody,nettyClientSession);
	    	
	    	
//			ctx.writeAndFlush(resp(req.getSubReqID()));
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
	        // �������쳣�͹ر�����  
	        logger.info("ChatClient:" + incoming.remoteAddress()+ "�쳣,�ѱ��������ر�");  
	        cause.printStackTrace();  
	        ctx.close();  
	    }  
}
