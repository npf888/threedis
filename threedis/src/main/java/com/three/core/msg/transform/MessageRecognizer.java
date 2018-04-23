package com.three.core.msg.transform;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.core.blocking.CGBlockingMsgService;
import com.three.core.blocking.GCBlockingMsgService;
import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.protobuf.ProtobufTransform;
import com.three.core.session.NettyClientSession;
import com.three.globals.InitService;

/**
 * 消息识别器
 * @author JavaServer
 *
 */
@Service
public class MessageRecognizer implements InitService{

	private Logger logger = Logger.getLogger(MessageRecognizer.class);

	//接收 消息的 service 初始化
	@Autowired
	private CGBlockingMsgService cgBlockingMsgService;
	//发送 消息的 service 初始化
	@Autowired
	private GCBlockingMsgService gcBlockingMsgService;
	@Autowired
	private ProtobufTransform protobufTransform;
	
	
	
	
	@Autowired
	private MsgProvider msgProvider;
	@Override
	public void init() {
		msgProvider.init();
		cgBlockingMsgService.init();
		gcBlockingMsgService.init();
	}
	
	
	
	/**
	 * 识别消息
	 */
	public void recognize(Integer msgcode, String jsonMsg,NettyClientSession nettyClientSession){
		IMessage message =getByMsgType(msgcode);
		IMessage msg =MsgTransform.fromJSONString(jsonMsg,message);
		if(msg.getMsgType() == IMessage.CG_MSG_TYPE){//请求消息
			msg.setNettyClientSession(nettyClientSession);
			cgBlockingMsgService.putMsgIntoCache(msg);
		}else if(msg.getMsgType() == IMessage.GC_MSG_TYPE){//返回消息
			logger.info("GC消息："+MsgTransform.toJSONString(msg));
		}
	}

	public void handlerGCMsg(IMessage msg) {
		gcBlockingMsgService.putMsgIntoCache(msg);
	}

	
	
	

	public ProtobufTransform getProtobufTransform() {
		return protobufTransform;
	}

	public IMessage getByMsgType(int msgcode){
		return this.msgProvider.getByMsgType(msgcode);
	}

	public  CGBlockingMsgService getCgBlockingMsgService() {
		return cgBlockingMsgService;
	}

	public  GCBlockingMsgService getGcBlockingMsgService() {
		return gcBlockingMsgService;
	}





	
}
