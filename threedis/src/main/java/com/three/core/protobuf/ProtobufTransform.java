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
	 *  ��protobuf ����Ϣ
	 * @param msg
	 * @param ctx
	 */
	public  void  toReadMsg(Globals globals,Message msg, ChannelHandlerContext ctx){
		
		SubcribeReqProto.SubcribeReq req = (SubcribeReqProto.SubcribeReq)msg;
    	String jsonBody = req.getJsonBody();
    	if(StringUtils.isEmpty(jsonBody)){
    		logger.info("[������Ϣ]��ǰ��ϢΪ�գ���Ϣ reqID:"+req.getMsgCode()+" --- ��Ϣ��:"+req.getJsonBody());
    		return;
    	}
    	logger.info("[������Ϣ]��ǰ��Ϣ reqID:"+req.getMsgCode()+" --- ��Ϣ��:"+req.getJsonBody());
    	
    	NettyClientSession nettyClientSession = globals.getNettyClientSessionMap(ctx.channel().remoteAddress().toString());
    	globals.getMessageRecognizer().recognize(req.getMsgCode(),jsonBody,nettyClientSession);
	}
	
	
	
	
	/**
	 * �� protobuf д��Ϣ
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
