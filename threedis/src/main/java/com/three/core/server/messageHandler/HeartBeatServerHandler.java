package com.three.core.server.messageHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {  
  
    private int loss_connect_time = 0;  
  
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)  
            throws Exception {  
        if (evt instanceof IdleStateEvent) {  
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.READER_IDLE) {  
                loss_connect_time++;  
                System.out.println("[60 ��û�н��յ��ͻ���" + ctx.channel().id()  
                        + "����Ϣ��]");  
                if (loss_connect_time > 2) {  
                    // ����20��û�������͹ر��������  
                    System.out.println("[�ر��������Ծ��channel:" + ctx.channel().id()  
                            + "]");  
                    ctx.channel().close();  
                }  
            }  
        } else {  
            super.userEventTriggered(ctx, evt);  
        }  
    }  

}
