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
 * server ������
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
    	  
        EventLoopGroup bossGroup = new NioEventLoopGroup();// boss�̳߳�  
        EventLoopGroup workerGroup = new NioEventLoopGroup();// worker�̳߳�  
        try {  
            ServerBootstrap b = new ServerBootstrap();  
            b.group(bossGroup, workerGroup)  
                    .channel(NioServerSocketChannel.class)// ʹ��TCP  
                    .option(ChannelOption.SO_BACKLOG, 128)// BACKLOG���ڹ��������׽���ServerSocket���󣬱�ʶ���������������߳�ȫ��ʱ��������ʱ���������������ֵ�����Ķ��е���󳤶ȡ����δ���û������õ�ֵС��1��Java��ʹ��Ĭ��ֵ50��  
                    .childOption(ChannelOption.SO_KEEPALIVE, true)// �Ƿ���������������ơ���˫��TCP�׽��ֽ������Ӻ󣨼�������ESTABLISHED״̬������������Сʱ�����ϲ�û���κ����ݴ��������£����׻��ƲŻᱻ���  
            		.childHandler(new ChannelInitializer(){

						@Override
						protected void initChannel(Channel ch) throws Exception {
							 ChannelPipeline pipeline = ch.pipeline();  
								pipeline.addLast("logging", new LoggingHandler(LogLevel.INFO));
						        // ----Protobuf������������������ǹؼ�----  
						        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());// ����decodeǰ��������ճ�����⣨���ð�ͷ�еİ������鳤����ʶ����ճ����  
						        //����Protobuf���봦��������Ϣ���յ��˾ͻ��Զ����룬ProtobufDecoder��netty�Դ��ģ�Message���Լ������Protobuf��  
						        pipeline.addLast("protobufDecoder", new ProtobufDecoder(SubcribeReqProto.SubcribeReq.getDefaultInstance()));  
						        // ���������л����ֽ�����ǰ����һ���򵥵İ�ͷ��ֻ�������л����ֽڳ��ȡ�  
						        pipeline.addLast("frameEncoder",   new ProtobufVarint32LengthFieldPrepender());  
						        //����Protobuf�����������͵���Ϣ���Ⱦ�������  
						        pipeline.addLast("protobufEncoder", new ProtobufEncoder());  
						        // ----Protobuf������END----  
						  
						        pipeline.addLast("handler", new ChatServerHandler(globals));//�Լ��������Ϣ��������������Ϣ��������ദ��  
//						        pipeline.addLast("ackHandler", new AckServerHandler());//����ACK  
						        pipeline.addLast("timeout", new IdleStateHandler(100, 0, 0,  
						                TimeUnit.SECONDS));// //������Ϊ�����������,60��鿴һ�����ߵĿͻ���channel�Ƿ����  
						        pipeline.addLast(new HeartBeatServerHandler());// ��������handler  
							
						}
            			
            		});// ��ʼ�����õĴ�����  
  
            logger.info("[ChatServer ������]");  
  
            // �󶨶˿ڣ���ʼ���ս���������  
            ChannelFuture f = b.bind(port).sync();  
  
            // �ȴ������� socket �ر� ��  
            // �ⲻ�ᷢ�����������ŵعرշ�������  
            f.channel().closeFuture().sync();  
  
        } finally {  
            workerGroup.shutdownGracefully();  
            bossGroup.shutdownGracefully();  
  
            logger.info("[ChatServer �ر���]");  
        }  
    }  
    
    
   
    
}
