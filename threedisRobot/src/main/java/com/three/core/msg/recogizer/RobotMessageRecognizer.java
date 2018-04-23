package com.three.core.msg.recogizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.msg.msgmap.RobotMsgProvider;
import com.three.core.msg.process.RobotMessageProcesser;
import com.three.globals.InitService;

/**
 * 消息识别器
 * @author JavaServer
 *
 */
@Component
public class RobotMessageRecognizer implements InitService{

	//消息识别器
	@Autowired
	private  RobotMessageProcesser robotMessageProcesser;
	//这个消息提供器   目前没有什么用  先放到这里
	@Autowired
	private RobotMsgProvider msgProvider;
	@Override
	public void init() {
		msgProvider.init();
		robotMessageProcesser.init();
		robotMessageProcesser.start();
	}
	
	



	public IMessage getByMsgType(int msgcode){
		return this.msgProvider.getByMsgType(msgcode);
	}



	/**
	 * 放置 消息
	 * @param gCMsg
	 */
	public void putMsg(IMessage gCMsg) {
		robotMessageProcesser.putMsg(gCMsg);
	}

	

	
}
