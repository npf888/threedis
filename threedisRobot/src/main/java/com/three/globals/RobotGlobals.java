package com.three.globals;

import com.three.core.msg.process.RobotMessageProcesser;
import com.three.core.msg.recogizer.RobotMessageRecognizer;
import com.three.core.robot.RobotSession;
import com.three.player.msg.CGLoginIn;


public class RobotGlobals {

	
	//��Ϣʶ����
	private static RobotMessageRecognizer messageRecognizer = new RobotMessageRecognizer();
	//��Ϣʶ����
	private static RobotMessageProcesser robotMessageProcesser = new RobotMessageProcesser();
	//������session
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
		//��һ�������� Ҫ��һ�� ��Ϣ
		robotMessageProcesser.putMsg(new CGLoginIn());
	}

	public static RobotSession getRobotSession() {
		return robotSession;
	}
	
		
		
}
