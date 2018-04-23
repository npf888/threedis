package com.three.core.server.messageHandler;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.protobuf.ProtobufTransform;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;

public class ChatServerHandler extends SimpleChannelInboundHandler<Message> {  
		Logger logger = Logger.getLogger(ChatServerHandler.class);
		
		private Globals globals;  
		
	    public ChatServerHandler(Globals globals) {
			this.globals=globals;
		}

		@Override  
	    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)  
	        Channel incoming = ctx.channel();  
	        logger.info("�û�:" + incoming.remoteAddress() + "����");  
	        NettyClientSession clientSession = new NettyClientSession(ctx);
	        clientSession.setGlobals(globals);
	        globals.setNettyClientSessionMap(clientSession);
	    } 
	    
	    @Override  
	    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  
	        Channel incoming = ctx.channel();  
	        logger.info("�û�:" + incoming.remoteAddress() + " �뿪\n");  
	        globals.removeNettyClientSessionMap(incoming.remoteAddress().toString());
	    }  
	  
	    @Override  
	    protected void channelRead0(ChannelHandlerContext ctx, Message msg)  
	            throws Exception {  
	    	
	    	globals.getMessageRecognizer().getProtobufTransform().toReadMsg(globals,msg,ctx);
	    	
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
