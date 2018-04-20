package com.three.core.protobuf;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.protobuf.Message;
import com.three.core.msg.inter.IMessage;
import com.three.core.msg.transform.MsgTransform;
import com.three.globals.Globals;


public class ProtobufRobotTransform {

	private static Logger logger = Logger.getLogger(ProtobufRobotTransform.class);
	
	
	/**
	 *  从protobuf 读消息
	 * @param msg
	 * @param ctx
	 */
	public static void toReadMsg(Message msg, ChannelHandlerContext ctx){
		
		SubcribeRespProto.SubcribeResp  resp = (SubcribeRespProto.SubcribeResp)msg;
    	
    	
    	String jsonBody = resp.getJsonBody();
    	if(StringUtils.isEmpty(jsonBody)){
    		logger.info("[解析消息]当前消息为空：消息 reqID:"+resp.getMsgCode()+" --- 消息体:"+resp.getJsonBody());
    		return;
    	}
    	logger.info("[解析消息]当前消息 reqID:"+resp.getMsgCode()+" --- 消息体:"+resp.getJsonBody());
    	IMessage message = Globals.getMessageRecognizer().getByMsgType(resp.getMsgCode());
    	IMessage GCMsg = MsgTransform.fromJSONString(jsonBody, message);
    	logger.info("GC消息："+GCMsg);
	}
	
	
	/**
	 * 向 protobuf 写消息
	 * @param msgType
	 * @param jsonMsg
	 * @return
	 */
	public static SubcribeReqProto.SubcribeReq  toWriteMsg(IMessage msg){
		
		return toWriteMsg(msg.getMsgCode(),MsgTransform.toJSONString(msg));
	}
	private static SubcribeReqProto.SubcribeReq  toWriteMsg(int msgType,String jsonMsg){
		return req(msgType,jsonMsg);
	}
	private static SubcribeReqProto.SubcribeReq  req(int msgType,String jsonMsg){
		SubcribeReqProto.SubcribeReq.Builder builder = SubcribeReqProto.SubcribeReq.newBuilder();
		builder.setMsgCode(msgType);
		builder.setJsonBody(jsonMsg);
		return builder.build();
	} 
}
