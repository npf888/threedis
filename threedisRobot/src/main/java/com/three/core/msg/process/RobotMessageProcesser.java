package com.three.core.msg.process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.core.msg.inter.IMessage;
import com.three.globals.InitService;
import com.three.globals.RobotGlobals;

@Component
public class RobotMessageProcesser extends Thread  implements InitService{

	@Autowired
	private RobotMessageHandlerFactory robotMessageHandlerFactory;
	
	private BlockingQueue<IMessage> robotMsgQueue = new LinkedBlockingDeque<IMessage>();
	
	@Override
	public void init(){
		
	}
	
	
	@Override
	public void run(){
		while(true){
			processMsg();
		}
	}


	//处理 GC的消息
	private void processMsg() {
		try {
			IMessage msg = robotMsgQueue.take();
			this.robotMessageHandlerFactory.getHandler(RobotGlobals.getRobot().getHandlerType()).handler(msg);;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//存入消息
	public void putMsg(IMessage msg){
		this.robotMsgQueue.offer(msg);
	}
}
