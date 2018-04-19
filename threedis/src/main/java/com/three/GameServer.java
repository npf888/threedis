package com.three;

import com.three.core.server.NettyServer;
import com.three.globals.Globals;

public class GameServer {

	
	
	 //启动
	 public static void main(String[] args){
		 	//全局变量
		 	Globals.init();
		 	
		 	//netty服务
	    	NettyServer server = new NettyServer(8080);
	    	try{
	    		server.run();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    	
	}
}
