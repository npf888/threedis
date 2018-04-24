package com.three.core.msg.msgMap;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mcode.percode.GiftCode;
import com.three.core.msg.mcode.percode.PlayerCode;
import com.three.gift.msg.CGSendGift;
import com.three.gift.msg.GCSendGift;
import com.three.globals.InitService;
import com.three.player.msg.CGLoginIn;

/**
 * 消息 的 code 和 消息本身的映射
 * @author JavaServer
 *
 */

@Service
public class MsgProvider implements InitService{
	
	private  Map<Integer,IMessage> msgMap = new HashMap<Integer,IMessage>();
	
	
	@Override
	public void init() {
		msgMap.put(GiftCode.CG_SEND_GIFT,new CGSendGift());
		msgMap.put(GiftCode.GC_SEND_GIFT,new GCSendGift());
		msgMap.put(PlayerCode.CG_LOGIN_IN,new CGLoginIn());
	}
	


	public IMessage getByMsgType(int msgType){
		return msgMap.get(msgType);
	}
	
	

	
}
