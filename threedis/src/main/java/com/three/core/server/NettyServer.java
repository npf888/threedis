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
 * server 启动类
 * @author JavaServer
 *
 */
public class NettyServer {
	Logger logger = Logger.getLogger(NettyServer.class);
	private int port;  
	private Globals globals;  
    public NettyServer(int port, Globals globals) {  
        this.port = port;  
        this.globals = globals;  
    } 
    
    public void run() throws Exception {  
    	  
        EventLoopGroup bossGroup = new NioEventLoopGroup();// boss线程池  
        EventLoopGroup workerGroup = new NioEventLoopGroup();// worker线程池  
        try {  
            ServerBootstrap b = new ServerBootstrap();  
            b.group(bossGroup, workerGroup)  
                    .channel(NioServerSocketChannel.class)// 使用TCP  
                    .option(ChannelOption.SO_BACKLOG, 128)// BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。  
                    .childOption(ChannelOption.SO_KEEPALIVE, true)// 是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。  
            		.childHandler(new ChannelInitializer(){

						@Override
						protected void initChannel(Channel ch) throws Exception {
							 ChannelPipeline pipeline = ch.pipeline();  
								pipeline.addLast("logging", new LoggingHandler(LogLevel.INFO));
						        // ----Protobuf处理器，这里的配置是关键----  
						        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());// 用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）  
						        //配置Protobuf解码处理器，消息接收到了就会自动解码，ProtobufDecoder是netty自带的，Message是自己定义的Protobuf类  
						        pipeline.addLast("protobufDecoder", new ProtobufDecoder(SubcribeReqProto.SubcribeReq.getDefaultInstance()));  
						        // 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。  
						        pipeline.addLast("frameEncoder",   new ProtobufVarint32LengthFieldPrepender());  
						        //配置Protobuf编码器，发送的消息会先经过编码  
						        pipeline.addLast("protobufEncoder", new ProtobufEncoder());  
						        // ----Protobuf处理器END----  
						  
						        pipeline.addLast("handler", new ChatServerHandler(globals));//自己定义的消息处理器，接收消息会在这个类处理  
//						        pipeline.addLast("ackHandler", new AckServerHandler());//处理ACK  
						        pipeline.addLast("timeout", new IdleStateHandler(100, 0, 0,  
						                TimeUnit.SECONDS));// //此两项为添加心跳机制,60秒查看一次在线的客户端channel是否空闲  
						        pipeline.addLast(new HeartBeatServerHandler());// 心跳处理handler  
							
						}
            			
            		});// 初始化配置的处理器  
  
            logger.info("[ChatServer 启动了]");  
  
            // 绑定端口，开始接收进来的连接  
            ChannelFuture f = b.bind(port).sync();  
  
            // 等待服务器 socket 关闭 。  
            // 这不会发生，可以优雅地关闭服务器。  
            f.channel().closeFuture().sync();  
  
        } finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
  
            logger.info("[ChatServer 关闭了]");  
        }  
    }  
    
    
   
    
}
