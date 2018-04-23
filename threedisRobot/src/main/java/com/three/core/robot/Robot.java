package com.three.core.robot;

import org.springframework.stereotype.Component;

import com.three.core.client.NettyClient;

/**
 * 机器人实体
 * @author JavaServer
 *
 */
@Component
public class Robot {

	
	private String handlerType;
	
	private NettyClient nettyClient = new NettyClient();
	
	public void init(String handlerType){
		this.handlerType=handlerType;
	}
	
	
	public void start(){
		try {
			nettyClient.clientStart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public String getHandlerType() {
		return handlerType;
	}
	
	
	
	
	
	
	
	
	
}
