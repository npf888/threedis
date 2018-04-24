package com.three.globals;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.core.msg.transform.MessageRecognizer;
import com.three.core.session.NettyClientSession;
import com.three.database.PersistentThread;


/**
 * 
 * 全局变量
 * @author JavaServer
 *
 */
@Service
public class Globals {

	
	//消息识别器
	private static MessageRecognizer messageRecognizer;
	//session 
	private static Map<String,NettyClientSession>  nettyClientSessionMap = new HashMap<String,NettyClientSession>();
	//数据库处理的线程
	private static PersistentThread persistentThread;
	public  void init(){
		
		messageRecognizer.init();
		persistentThread.init();
	}

	
	
	
	
	
	@Autowired
	public void setMessageRecognizer(MessageRecognizer messageRecognizer) {
		Globals.messageRecognizer = messageRecognizer;
	}

	@Autowired
	public static void setPersistentThread(PersistentThread persistentThread) {
		Globals.persistentThread = persistentThread;
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
