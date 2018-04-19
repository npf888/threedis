package com.three.core.msg.transform;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;
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
	
	
	
	/**
	 * ʶ����Ϣ
	 */
	public void recognize(Integer code,String jsonMsg,NettyClientSession nettyClientSession){
		Class<?> clazz = this.msgProvider.getByMsgType(code);
		IMessage msg =MsgTransform.fromJSONString(jsonMsg,clazz);
		msg.setNettyClientSession(nettyClientSession);
		Globals.getCgBlockingMsgService().putMsgIntoCache(msg);
	}


	
}
