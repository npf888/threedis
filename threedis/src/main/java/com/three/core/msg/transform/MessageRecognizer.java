package com.three.core.msg.transform;

import org.apache.log4j.Logger;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;
import com.three.globals.InitService;

/**
 * 消息识别器
 * @author JavaServer
 *
 */
public class MessageRecognizer implements InitService{

	private Logger logger = Logger.getLogger(MessageRecognizer.class);
	//这个消息提供器   目前没有什么用  先放到这里
	private MsgProvider msgProvider = new MsgProvider();
	@Override
	public void init() {
		msgProvider.init();
	}
	
	
	
	/**
	 * 识别消息
	 */
	public void recognize(Integer msgcode, String jsonMsg,NettyClientSession nettyClientSession){
		IMessage message =getByMsgType(msgcode);
		IMessage msg =MsgTransform.fromJSONString(jsonMsg,message);
		if(msg.getMsgType() == IMessage.CG_MSG_TYPE){//请求消息
			msg.setNettyClientSession(nettyClientSession);
			Globals.getCgBlockingMsgService().putMsgIntoCache(msg);
		}else if(msg.getMsgType() == IMessage.GC_MSG_TYPE){//返回消息
			logger.info("GC消息："+MsgTransform.toJSONString(msg));
		}
	}




	public IMessage getByMsgType(int msgcode){
		return this.msgProvider.getByMsgType(msgcode);
	}



	
}
