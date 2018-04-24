package com.three.core.protobuf;

import org.springframework.stereotype.Service;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.transform.MsgTransform;

@Service
public class ProtobufTransform {

	
	
	/**
	 * protobuf  转换消息 
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
