package com.three.core.msg.msgMap;

import java.util.HashMap;
import java.util.Map;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mcode.percode.GiftCode;
import com.three.core.msg.mcode.percode.PlayerCode;
import com.three.gift.msg.CGSendGift;
import com.three.globals.InitService;
import com.three.player.msg.CGLoginIn;

/**
 * 消息提供器
 * @author JavaServer
 *
 */
public class MsgProvider implements InitService{
	
	private  Map<Integer,IMessage> msgMap = new HashMap<Integer,IMessage>();
	
	
	/**
	 * 初始化消息
	 */
	@Override
	public void init() {
		msgMap.put(GiftCode.CG_SEND_GIFT,new CGSendGift());
		msgMap.put(PlayerCode.CG_LOGIN_IN,new CGLoginIn());
	}
	


	/**
	 * 获取消息
	 * @param msgType
	 * @return
	 */
	public IMessage getByMsgType(int msgType){
		return msgMap.get(msgType);
	}
	
	

	
}
