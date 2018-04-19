package com.three.core.msg.msgMap;

import java.util.HashMap;
import java.util.Map;

import com.three.core.msg.mtype.perType.GiftType;
import com.three.core.msg.mtype.perType.PlayerType;
import com.three.gift.msg.CGSendGift;
import com.three.globals.InitService;
import com.three.player.msg.CGLoginIn;

/**
 * 消息提供器
 * @author JavaServer
 *
 */
public class MsgProvider implements InitService{
	
	private  Map<Integer,Class<?>> msgMap = new HashMap<Integer,Class<?>>();
	
	
	/**
	 * 初始化消息
	 */
	@Override
	public void init() {
		msgMap.put(GiftType.CG_SEND_GIFT,CGSendGift.class);
		msgMap.put(PlayerType.CG_LOGIN_IN,CGLoginIn.class);
		
	}
	
	
	/**
	 * 获取消息
	 * @param msgType
	 * @return
	 */
	public Class<?> getByMsgType(int msgType){
		return msgMap.get(msgType);
	}
	
	

	
}
