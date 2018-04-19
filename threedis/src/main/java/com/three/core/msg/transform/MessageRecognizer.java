package com.three.core.msg.transform;

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

	//这个消息提供器   目前没有什么用  先放到这里
	private MsgProvider msgProvider = new MsgProvider();
	@Override
	public void init() {
		msgProvider.init();
	}
	
	
	
	/**
	 * 识别消息
	 */
	public void recognize(Integer code,String jsonMsg,NettyClientSession nettyClientSession){
		Class<?> clazz = this.msgProvider.getByMsgType(code);
		IMessage msg =MsgTransform.fromJSONString(jsonMsg,clazz);
		msg.setNettyClientSession(nettyClientSession);
		Globals.getCgBlockingMsgService().putMsgIntoCache(msg);
	}


	
}
