package com.three.globals;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.core.msg.transform.MessageRecognizer;
import com.three.core.session.NettyClientSession;
import com.three.database.persist.PersistService;
import com.three.player.service.OnLineService;


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
	//持久化的服务
	private static PersistService persistService;
	//在线用户 service
	private static OnLineService onLineService;
	
	
	
	public  void init(){
		
		messageRecognizer.init();
		persistService.init();
		onLineService.init();
	}

	
	
	
	
	
	public static OnLineService getOnLineService() {
		return onLineService;
	}

	@Autowired
	public  void setOnLineService(OnLineService onLineService) {
		Globals.onLineService = onLineService;
	}





	@Autowired
	public void setMessageRecognizer(MessageRecognizer messageRecognizer) {
		Globals.messageRecognizer = messageRecognizer;
	}


	public static PersistService getPersistService() {
		return persistService;
	}
	
	@Autowired
	public void setPersistService(PersistService persistService) {
		Globals.persistService = persistService;
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
