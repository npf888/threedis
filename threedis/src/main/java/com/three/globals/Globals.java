package com.three.globals;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.transform.MessageRecognizer;
import com.three.core.session.NettyClientSession;


/**
 * 
 * 全局变量
 * @author JavaServer
 *
 */
@Service
public class Globals {

	
	//消息识别器 (再set方法中注入)
	private static MessageRecognizer messageRecognizer;
	//session 集合
	private static Map<String,NettyClientSession>  nettyClientSessionMap = new HashMap<String,NettyClientSession>();
	
	public  void init(){
		
		messageRecognizer.init();
	}

	
	
	
	
	
	@Autowired
	public void setMessageRecognizer(MessageRecognizer messageRecognizer) {
		Globals.messageRecognizer = messageRecognizer;
	}

	
	

	public static  MessageRecognizer getMessageRecognizer() {
		return messageRecognizer;
	}
	public  static NettyClientSession getNettyClientSessionMap(String ip) {
		return nettyClientSessionMap.get(ip);
	}
	public static NettyClientSession setNettyClientSessionMap(NettyClientSession clientSession) {
		return nettyClientSessionMap.put(clientSession.getClientIp(), clientSession);
	}
	public static void removeNettyClientSessionMap(String clientIp) {
		nettyClientSessionMap.remove(clientIp);
	}

	
	
	
	
	
	
}
