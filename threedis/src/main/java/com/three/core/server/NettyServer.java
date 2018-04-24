package com.three.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.three.core.blocking.CGBlockingMsgService;
import com.three.core.protobuf.SubcribeReqProto;
import com.three.core.server.messageHandler.ChatServerHandler;
import com.three.core.server.messageHandler.HeartBeatServerHandler;
import com.three.globals.Globals;

/**
 * server 
 * @author JavaServer
 *
 */
public class NettyServer {
	Logger logger = Logger.getLogger(NettyServer.class);
	private int port;  
    public NettyServer(int port) {  
        this.port = port;  
    } 
    
    public void run() throws Exception {  
    	  
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {  
            ServerBootstrap b = new ServerBootstrap();  
            b.group(bossGroup, workerGroup)  
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
            		.childHandler(new ChannelInitializer(){

						@Override
						protected void initChannel(Channel ch) throws Exception {
							 ChannelPipeline pipeline = ch.pipeline();  
								pipeline.addLast("logging", new LoggingHandler(LogLevel.INFO));
						        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
						        pipeline.addLast("protobufDecoder", new ProtobufDecoder(SubcribeReqProto.SubcribeReq.getDefaultInstance()));  
						        pipeline.addLast("frameEncoder",   new ProtobufVarint32LengthFieldPrepender());  
						        pipeline.addLast("protobufEncoder", new ProtobufEncoder());  
						  
						        pipeline.addLast("handler", new ChatServerHandler());
//						        pipeline.addLast("ackHandler", new AckServerHandler());
						        pipeline.addLast("timeout", new IdleStateHandler(100, 0, 0,  
						                TimeUnit.SECONDS));
						        pipeline.addLast(new HeartBeatServerHandler());
							
						}
            			
            		});
  
            logger.info("[ChatServer ������]");  
  
            ChannelFuture f = b.bind(port).sync();  
  
            f.channel().closeFuture().sync();  
  
        } finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
  
            logger.info("[ChatServer �ر���]");  
        }  
    }  
    
    
   
    
}
