package com.three;

import com.three.core.server.NettyServer;
import com.three.globals.Globals;

public class GameServer {

	
	
	 //����
	 public static void main(String[] args){
		 	//ȫ�ֱ���
		 	Globals.init();
		 	
		 	//netty����
	    	NettyServer server = new NettyServer(8080);
	    	try{
	    		server.run();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    	
	}
}
