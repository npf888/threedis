package com.three;

import com.three.core.msg.process.RobotMessageHandlerFactory;
import com.three.core.robot.Robot;
import com.three.core.spring.SpringContextRobotUtils;
import com.three.globals.RobotGlobals;

public class RobotClient {

	
	public static void main(String[] args) throws InterruptedException{
		
		SpringContextRobotUtils springUtils = new SpringContextRobotUtils();
	 	RobotGlobals globals = (RobotGlobals)springUtils.getBean(RobotGlobals.class);
	 	//初始化 消息的 type类型 和 处理消息的线程
	 	globals.init();
	 	Robot robot = RobotGlobals.getRobot();
		//当前处理 关于礼物的 业务
		robot.init(RobotMessageHandlerFactory.handler_type_gift);
		robot.start();
		
	}
}
