package com.three.core.msg.transform;

import org.apache.log4j.Logger;

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

	private Logger logger = Logger.getLogger(MessageRecognizer.class);
	//�����Ϣ�ṩ��   Ŀǰû��ʲô��  �ȷŵ�����
	private MsgProvider msgProvider = new MsgProvider();
	@Override
	public void init() {
		msgProvider.init();
	}
	
	
	
	/**
	 * ʶ����Ϣ
	 */
	public void recognize(Integer msgcode, String jsonMsg,NettyClientSession nettyClientSession){
		IMessage message =getByMsgType(msgcode);
		IMessage msg =MsgTransform.fromJSONString(jsonMsg,message);
		if(msg.getMsgType() == IMessage.CG_MSG_TYPE){//������Ϣ
			msg.setNettyClientSession(nettyClientSession);
			Globals.getCgBlockingMsgService().putMsgIntoCache(msg);
		}else if(msg.getMsgType() == IMessage.GC_MSG_TYPE){//������Ϣ
			logger.info("GC��Ϣ��"+MsgTransform.toJSONString(msg));
		}
	}




	public IMessage getByMsgType(int msgcode){
		return this.msgProvider.getByMsgType(msgcode);
	}



	
}
