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
	 *  ��protobuf ����Ϣ
	 * @param msg
	 * @param ctx
	 */
	public static void toReadMsg(Message msg, ChannelHandlerContext ctx){
		
		SubcribeRespProto.SubcribeResp  resp = (SubcribeRespProto.SubcribeResp)msg;
    	
    	
    	String jsonBody = resp.getJsonBody();
    	if(StringUtils.isEmpty(jsonBody)){
    		logger.info("[������Ϣ]��ǰ��ϢΪ�գ���Ϣ reqID:"+resp.getMsgCode()+" --- ��Ϣ��:"+resp.getJsonBody());
    		return;
    	}
    	logger.info("[������Ϣ]��ǰ��Ϣ reqID:"+resp.getMsgCode()+" --- ��Ϣ��:"+resp.getJsonBody());
    	IMessage message = Globals.getMessageRecognizer().getByMsgType(resp.getMsgCode());
    	IMessage GCMsg = MsgTransform.fromJSONString(jsonBody, message);
    	logger.info("GC��Ϣ��"+GCMsg);
	}
	
	
	/**
	 * �� protobuf д��Ϣ
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
