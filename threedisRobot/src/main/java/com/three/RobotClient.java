package com.three;

import com.three.core.robot.Robot;

public class RobotClient {

	
	public static void main(String[] args) throws InterruptedException{
		Robot robot = new Robot();
		robot.init();
		robot.start();
		
	}
}
