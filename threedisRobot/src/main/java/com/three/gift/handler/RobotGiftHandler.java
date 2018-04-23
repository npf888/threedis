package com.three.gift.handler;

import org.springframework.stereotype.Component;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.process.RobotBaseMessageHandler;
import com.three.gift.msg.GCSendGift;

//处理消息的 handler 
@Component
public class RobotGiftHandler extends RobotBaseMessageHandler{

	@Override
	public void handlerSub(IMessage msg) {
		if(msg instanceof GCSendGift){
			
		}
	}

	

}
