package com.three;

import com.three.core.server.NettyServer;
import com.three.core.spring.SpringContextUtils;
import com.three.globals.Globals;

public class GameServer {

	
	
	 public static void main(String[] args){
		 
	 	SpringContextUtils springUtils = new SpringContextUtils();
	 	Globals globals = (Globals)springUtils.getBean(Globals.class);
	 	globals.init();
	 	
    	NettyServer server = new NettyServer(8080);
    	try{
    		server.run();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	    	
	    	
	}
}
