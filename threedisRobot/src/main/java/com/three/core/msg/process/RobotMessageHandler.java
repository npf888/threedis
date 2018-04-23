package com.three.core.msg.process;

import com.three.core.msg.inter.IMessage;
import com.three.globals.RobotGlobals;
import com.three.player.msg.CGLoginIn;

public class RobotMessageHandler {

	
	//处理消息
	public void handler(IMessage msg){
		if(msg instanceof CGLoginIn){
			
		//}else if(msg instanceof GCLoginIn){//这个必须要有
			
			
			//执行具体的业务  比如 发送 new CGSendGift()
		}
		
		
		
		//把消息放到session 中
		RobotGlobals.getRobotSession().put(msg);
	}
}
