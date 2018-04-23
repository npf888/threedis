package com.three.core.msg.recogizer;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.msg.msgmap.RobotMsgProvider;
import com.three.globals.InitService;

/**
 * 消息识别器
 * @author JavaServer
 *
 */
public class RobotMessageRecognizer implements InitService{

	//这个消息提供器   目前没有什么用  先放到这里
	private RobotMsgProvider msgProvider = new RobotMsgProvider();
	@Override
	public void init() {
		msgProvider.init();
	}
	
	



	public IMessage getByMsgType(int msgcode){
		return this.msgProvider.getByMsgType(msgcode);
	}



	
}
