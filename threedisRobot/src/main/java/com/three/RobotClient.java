package com.three;

import com.three.core.msg.process.RobotMessageHandlerFactory;
import com.three.core.robot.Robot;
import com.three.core.spring.SpringContextRobotUtils;
import com.three.globals.RobotGlobals;

public class RobotClient {

	
	public static void main(String[] args) throws InterruptedException{
		
		SpringContextRobotUtils springUtils = new SpringContextRobotUtils();
	 	RobotGlobals globals = (RobotGlobals)springUtils.getBean(RobotGlobals.class);
	 	//��ʼ�� ��Ϣ�� type���� �� ������Ϣ���߳�
	 	globals.init();
	 	Robot robot = RobotGlobals.getRobot();
		//��ǰ���� ��������� ҵ��
		robot.init(RobotMessageHandlerFactory.handler_type_gift);
		robot.start();
		
	}
}
