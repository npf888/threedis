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

	
	//��Ϣʶ����
	@Autowired
	private MessageRecognizer messageRecognizer;
	//session ����
	private Map<String,NettyClientSession>  nettyClientSessionMap = new HashMap<String,NettyClientSession>();
	
	public  void init(){
		
		messageRecognizer.init();
	}

	
	
	
	
	
	
	
	//����Ҫ���ص���Ϣ
	public void handlerGCMsg(IMessage msg){
		getMessageRecognizer().handlerGCMsg(msg);
	}
	
	
	
	
	
	
	
	

	public  MessageRecognizer getMessageRecognizer() {
		return messageRecognizer;
	}
	public  NettyClientSession getNettyClientSessionMap(String ip) {
		return nettyClientSessionMap.get(ip);
	}
	public  NettyClientSession setNettyClientSessionMap(NettyClientSession clientSession) {
		return nettyClientSessionMap.put(clientSession.getClientIp(), clientSession);
	}
	public  void removeNettyClientSessionMap(String clientIp) {
		nettyClientSessionMap.remove(clientIp);
	}

	
	
	
	
	
	
}
