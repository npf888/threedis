package com.three.core.msg.process;

import com.three.core.msg.inter.IMessage;
import com.three.globals.RobotGlobals;
import com.three.player.msg.CGLoginIn;

/**
 * 处理一些公共消息
 * @author JavaServer
 *
 */
public  abstract class RobotBaseMessageHandler  implements RobotMessageHandler{

	
	/**
	 * 父 类 处理公共 消息  比如登录消息
	 */
	@Override
	public void handler(IMessage msg) {
		if(msg instanceof CGLoginIn){
			
			//}else if(msg instanceof GCLoginIn){//这个必须要有
				
				
				//执行具体的业务  比如 发送 new CGSendGift()
		}
			
		handlerSub(msg);
			
		//把消息放到session 中
		RobotGlobals.getRobotSession().put(msg);
		
	}
	
	
	
	public abstract void handlerSub(IMessage msg);
		
	
}
