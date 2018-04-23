package com.three.core.robot;

import com.three.core.client.NettyClient;
import com.three.globals.RobotGlobals;

/**
 * 机器人实体
 * @author JavaServer
 *
 */
public class Robot {

	private NettyClient nettyClient = new NettyClient();
	
	//初始化
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
