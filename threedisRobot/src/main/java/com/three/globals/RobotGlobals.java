package com.three.globals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.core.msg.recogizer.RobotMessageRecognizer;
import com.three.core.robot.Robot;
import com.three.core.robot.RobotSession;
import com.three.player.msg.CGLoginIn;

@Component
public class RobotGlobals {

	private static Robot robot;
	
	
	//消息识别器
	private static RobotMessageRecognizer robotMessageRecognizer ;
	
	//机器人session
	private static RobotSession robotSession = null;

	public  void init(){
		robotMessageRecognizer.init();
		
	}
	
	
	
	
	

	@Autowired
	public void setRobot(Robot robot) {
		RobotGlobals.robot = robot;
	}
	
	public static Robot getRobot() {
		return robot;
	}

	
	
	
	
	public static RobotMessageRecognizer getRobotMessageRecognizer() {
		return robotMessageRecognizer;
	}
	@Autowired
	public void setRobotMessageRecognizer(
			RobotMessageRecognizer robotMessageRecognizer) {
		RobotGlobals.robotMessageRecognizer = robotMessageRecognizer;
	}






	public static void setRobotSession(RobotSession tRobotSession){
		robotSession=tRobotSession;
		//第一次连接上 要放一个 消息
		CGLoginIn cgLigin = new CGLoginIn();
		cgLigin.setDeviceMac("123456");
		robotMessageRecognizer.putMsg(cgLigin);
	}

	public static RobotSession getRobotSession() {
		return robotSession;
	}
	
		
		
}
