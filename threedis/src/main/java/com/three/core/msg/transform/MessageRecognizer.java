package com.three.core.msg.transform;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.Message;
import com.three.core.blocking.CGBlockingMsgService;
import com.three.core.blocking.GCBlockingMsgService;
import com.three.core.msg.inter.IMessage;
import com.three.core.msg.msgMap.MsgProvider;
import com.three.core.protobuf.ProtobufTransform;
import com.three.core.protobuf.SubcribeReqProto;
import com.three.core.protobuf.SubcribeRespProto;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;
import com.three.globals.InitService;

/**
 * 消息识别器
 * @author JavaServer
 *
 */
@Service
public class MessageRecognizer implements InitService{

	private Logger logger = Logger.getLogger(MessageRecognizer.class);

	//处理  从protobuf接收 消息
	@Autowired
	private CGBlockingMsgService cgBlockingMsgService;
	//处理  protobuf 返回去消息
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
	
	
	


	//读 protobuf 的消息 转 json
	public void toReadMsg(Message msg,ChannelHandlerContext ctx){
		SubcribeReqProto.SubcribeReq req = (SubcribeReqProto.SubcribeReq)msg;
    	String jsonBody = req.getJsonBody();
    	if(StringUtils.isEmpty(jsonBody)){
    		logger.info("[������Ϣ]��ǰ��ϢΪ�գ���Ϣ reqID:"+req.getMsgCode()+" --- ��Ϣ��:"+req.getJsonBody());
    		return;
    	}
    	logger.info("[������Ϣ]��ǰ��Ϣ reqID:"+req.getMsgCode()+" --- ��Ϣ��:"+req.getJsonBody());
		NettyClientSession nettyClientSession = Globals.getNettyClientSessionMap(ctx.channel().remoteAddress().toString());
    	recognize(req.getMsgCode(),jsonBody,nettyClientSession);
	}
	// 识别消息
	public void recognize(Integer msgcode, String jsonMsg,NettyClientSession nettyClientSession){
		IMessage message =getByMsgType(msgcode);
		IMessage msg =MsgTransform.fromJSONString(jsonMsg,message);
		if(msg.getMsgType() == IMessage.CG_MSG_TYPE){//������Ϣ
			msg.setNettyClientSession(nettyClientSession);
			cgBlockingMsgService.putMsgIntoCache(msg);
		}else if(msg.getMsgType() == IMessage.GC_MSG_TYPE){//������Ϣ
			logger.info("GC��Ϣ��"+MsgTransform.toJSONString(msg));
		}
	}
	
	
	// 将消息 转成 protobuf的 格式
	public SubcribeRespProto.SubcribeResp  toWriteMsg(IMessage msg){
		return this.protobufTransform.toWriteMsg(msg);
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
