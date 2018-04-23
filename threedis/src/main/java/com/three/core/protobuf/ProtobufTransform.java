package com.three.core.protobuf;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.protobuf.Message;
import com.three.core.msg.inter.IMessage;
import com.three.core.msg.transform.MsgTransform;
import com.three.core.session.NettyClientSession;
import com.three.globals.Globals;

@Service
public class ProtobufTransform {

	
	private Logger logger = Logger.getLogger(ProtobufTransform.class);
	
	/**
	 *  从protobuf 读消息
	 * @param msg
	 * @param ctx
	 */
	public  void  toReadMsg(Globals globals,Message msg, ChannelHandlerContext ctx){
		
		SubcribeReqProto.SubcribeReq req = (SubcribeReqProto.SubcribeReq)msg;
    	String jsonBody = req.getJsonBody();
    	if(StringUtils.isEmpty(jsonBody)){
    		logger.info("[解析消息]当前消息为空：消息 reqID:"+req.getMsgCode()+" --- 消息体:"+req.getJsonBody());
    		return;
    	}
    	logger.info("[解析消息]当前消息 reqID:"+req.getMsgCode()+" --- 消息体:"+req.getJsonBody());
    	
    	NettyClientSession nettyClientSession = globals.getNettyClientSessionMap(ctx.channel().remoteAddress().toString());
    	globals.getMessageRecognizer().recognize(req.getMsgCode(),jsonBody,nettyClientSession);
	}
	
	
	
	
	/**
	 * 向 protobuf 写消息
	 * @param msgType
	 * @param jsonMsg
	 * @return
	 */
	public  SubcribeRespProto.SubcribeResp  toWriteMsg(IMessage msg){
		return toWriteMsg(msg.getMsgCode(),MsgTransform.toJSONString(msg));
	}
	private  SubcribeRespProto.SubcribeResp  toWriteMsg(int msgType,String jsonMsg){
		return resp(msgType,jsonMsg);
	}
	private  SubcribeRespProto.SubcribeResp resp(int msgType,String jsonMsg){
		SubcribeRespProto.SubcribeResp.Builder builder = SubcribeRespProto.SubcribeResp.newBuilder();
		builder.setMsgCode(msgType);
		builder.setJsonBody(jsonMsg);
		return builder.build();
	} 
}
