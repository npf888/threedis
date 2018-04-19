package com.three.globals;

import java.util.HashMap;
import java.util.Map;

import com.three.core.blocking.CGBlockingMsgService;
import com.three.core.blocking.GCBlockingMsgService;
import com.three.core.msg.transform.MessageRecognizer;
import com.three.core.session.NettyClientSession;


/**
 * 
 * 全局变量
 * @author JavaServer
 *
 */
public class Globals {

	//接收 消息的 service 初始化
	private static CGBlockingMsgService cgBlockingMsgService = new CGBlockingMsgService();
	//发送 消息的 service 初始化
	private static GCBlockingMsgService gcBlockingMsgService = new GCBlockingMsgService();
	
	//消息识别器
	private static MessageRecognizer messageRecognizer = new MessageRecognizer();
	//session 集合
	private static Map<String,NettyClientSession>  nettyClientSessionMap = new HashMap<String,NettyClientSession>();
	
	public static void init(){
		cgBlockingMsgService.init();
		gcBlockingMsgService.init();
		messageRecognizer.init();
	}

	
	
	
	
	
	public static CGBlockingMsgService getCgBlockingMsgService() {
		return cgBlockingMsgService;
	}

	public static GCBlockingMsgService getGcBlockingMsgService() {
		return gcBlockingMsgService;
	}

	public static MessageRecognizer getMessageRecognizer() {
		return messageRecognizer;
	}
	public static NettyClientSession getNettyClientSessionMap(String ip) {
		return nettyClientSessionMap.get(ip);
	}
	public static NettyClientSession setNettyClientSessionMap(NettyClientSession clientSession) {
		return nettyClientSessionMap.put(clientSession.getClientIp(), clientSession);
	}
	public static void removeNettyClientSessionMap(String clientIp) {
		nettyClientSessionMap.remove(clientIp);
	}

	
	
	
	
	
	
}
