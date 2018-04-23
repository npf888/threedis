package com.three.core.msg.process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.three.core.msg.inter.IMessage;
import com.three.globals.InitService;

public class RobotMessageProcesser extends Thread  implements InitService{

	RobotMessageHandler msgHandler = new RobotMessageHandler();
	
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


	//���� GC����Ϣ
	private void processMsg() {
		try {
			IMessage msg = robotMsgQueue.take();
			this.msgHandler.handler(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//������Ϣ
	public void putMsg(IMessage msg){
		this.robotMsgQueue.offer(msg);
	}
}
