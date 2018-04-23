package com.three.core.msg.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.core.msg.inter.IMessage;
import com.three.globals.RobotGlobals;
import com.three.player.msg.CGLoginIn;

@Component
public class RobotMessageHandlerFactory {

	public static final String handler_type_gift = "gift";
	
	@Autowired
	private  RobotMessageHandler robotGiftHandler;
	
	
	
	public RobotMessageHandler getHandler(String htype){
		if(htype.equals(RobotMessageHandlerFactory.handler_type_gift)){
			return robotGiftHandler;
		}else if(htype.equals(RobotMessageHandlerFactory.handler_type_gift)){
			return robotGiftHandler;
		}
		return null;
	}
}
