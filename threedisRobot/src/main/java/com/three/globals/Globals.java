package com.three.globals;

import com.three.core.msg.recogizer.MessageRecognizer;


public class Globals {

	
	//��Ϣʶ����
	private static MessageRecognizer messageRecognizer = new MessageRecognizer();

	public static void init(){
		messageRecognizer.init();
	}
	
	
	
	public static MessageRecognizer getMessageRecognizer() {
		return messageRecognizer;
	}
		
		
		
		
}
