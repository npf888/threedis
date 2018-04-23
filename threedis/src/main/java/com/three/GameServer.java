package com.three;

import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.server.NettyServer;
import com.three.core.spring.SpringContextUtils;
import com.three.globals.Globals;

public class GameServer {

	
	
	 //启动
	 public static void main(String[] args){
		 
	 	SpringContextUtils springUtils = new SpringContextUtils();
	 	Globals globals = (Globals)springUtils.getBean(Globals.class);
	 	//初始化 消息的 type类型 和 处理消息的线程
	 	globals.init();
	 	
	 	//netty服务
    	NettyServer server = new NettyServer(8080,globals);
    	try{
    		server.run();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	    	
	    	
	}
}
