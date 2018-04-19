package com.three;

import com.three.core.server.NettyServer;

public class GameServer {

	 //Æô¶¯
	 public static void main(String[] args){
	    	NettyServer server = new NettyServer(8080);
	    	try{
	    		server.run();
	    		
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    	
	}
}
