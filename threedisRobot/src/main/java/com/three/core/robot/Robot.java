package com.three.core.robot;

import com.three.core.client.NettyClient;
import com.three.globals.RobotGlobals;

/**
 * ������ʵ��
 * @author JavaServer
 *
 */
public class Robot {

	private NettyClient nettyClient = new NettyClient();
	
	//��ʼ��
	public void init(){
		RobotGlobals.init();
	}
	
	
	public void start(){
		try {
			nettyClient.clientStart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
