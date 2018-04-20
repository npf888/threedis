package com.three.globals;

import com.three.core.msg.recogizer.MessageRecognizer;


public class Globals {

	
	//消息识别器
	private static MessageRecognizer messageRecognizer = new MessageRecognizer();

	public static void init(){
		messageRecognizer.init();
	}
	
	
	
	public static MessageRecognizer getMessageRecognizer() {
		return messageRecognizer;
	}
		
		
		
		
}
