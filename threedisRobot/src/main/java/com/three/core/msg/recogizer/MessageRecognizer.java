package com.three.core.msg.recogizer;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.globals.InitService;

/**
 * ��Ϣʶ����
 * @author JavaServer
 *
 */
public class MessageRecognizer implements InitService{

	//�����Ϣ�ṩ��   Ŀǰû��ʲô��  �ȷŵ�����
	private MsgProvider msgProvider = new MsgProvider();
	@Override
	public void init() {
		msgProvider.init();
	}
	
	



	public IMessage getByMsgType(int msgcode){
		return this.msgProvider.getByMsgType(msgcode);
	}



	
}
