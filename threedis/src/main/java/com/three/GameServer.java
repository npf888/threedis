package com.three;

import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.server.NettyServer;
import com.three.core.spring.SpringContextUtils;
import com.three.globals.Globals;

public class GameServer {

	
	
	 //����
	 public static void main(String[] args){
		 
	 	SpringContextUtils springUtils = new SpringContextUtils();
	 	Globals globals = (Globals)springUtils.getBean(Globals.class);
	 	//��ʼ�� ��Ϣ�� type���� �� ������Ϣ���߳�
	 	globals.init();
	 	
	 	//netty����
    	NettyServer server = new NettyServer(8080,globals);
    	try{
    		server.run();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	    	
	    	
	}
}
