package com.three.globals;

import com.three.core.msg.process.RobotMessageProcesser;
import com.three.core.msg.recogizer.RobotMessageRecognizer;
import com.three.core.robot.RobotSession;
import com.three.player.msg.CGLoginIn;


public class RobotGlobals {

	
	//消息识别器
	private static RobotMessageRecognizer messageRecognizer = new RobotMessageRecognizer();
	//消息识别器
	private static RobotMessageProcesser robotMessageProcesser = new RobotMessageProcesser();
	//机器人session
	private static RobotSession robotSession = null;

	public static void init(){
		messageRecognizer.init();
		robotMessageProcesser.init();
		robotMessageProcesser.start();
	}
	
	
	
	public static RobotMessageRecognizer getMessageRecognizer() {
		return messageRecognizer;
	}
		
	
	public static RobotMessageProcesser getRobotMessageProcesser() {
		return robotMessageProcesser;
	}
	
	
	public static void setRobotSession(RobotSession tRobotSession){
		robotSession=tRobotSession;
		//第一次连接上 要放一个 消息
		robotMessageProcesser.putMsg(new CGLoginIn());
	}

	public static RobotSession getRobotSession() {
		return robotSession;
	}
	
		
		
}
