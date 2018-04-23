package com.three.core.msg.recogizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.msg.msgmap.RobotMsgProvider;
import com.three.core.msg.process.RobotMessageProcesser;
import com.three.globals.InitService;

/**
 * ��Ϣʶ����
 * @author JavaServer
 *
 */
@Component
public class RobotMessageRecognizer implements InitService{

	//��Ϣʶ����
	@Autowired
	private  RobotMessageProcesser robotMessageProcesser;
	//�����Ϣ�ṩ��   Ŀǰû��ʲô��  �ȷŵ�����
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
	 * ���� ��Ϣ
	 * @param gCMsg
	 */
	public void putMsg(IMessage gCMsg) {
		robotMessageProcesser.putMsg(gCMsg);
	}

	

	
}
