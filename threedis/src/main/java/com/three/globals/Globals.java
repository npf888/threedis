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
 * ȫ�ֱ���
 * @author JavaServer
 *
 */
@Service
public class Globals {

	
	//��Ϣʶ���� (��set������ע��)
	private static MessageRecognizer messageRecognizer;
	//session ����
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
