package com.three.core.msg.msgMap;

import java.util.HashMap;
import java.util.Map;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mtype.perType.GiftType;
import com.three.core.msg.mtype.perType.PlayerType;
import com.three.gift.msg.CGSendGift;
import com.three.globals.InitService;
import com.three.player.msg.CGLoginIn;

/**
 * ��Ϣ�ṩ��
 * @author JavaServer
 *
 */
public class MsgProvider implements InitService{
	
	private  Map<Integer,IMessage> msgMap = new HashMap<Integer,IMessage>();
	
	
	/**
	 * ��ʼ����Ϣ
	 */
	@Override
	public void init() {
		msgMap.put(GiftType.CG_SEND_GIFT,new CGSendGift());
		msgMap.put(PlayerType.CG_LOGIN_IN,new CGLoginIn());
		
	}
	
	
	/**
	 * ��ȡ��Ϣ
	 * @param msgType
	 * @return
	 */
	public IMessage getByMsgType(int msgType){
		return msgMap.get(msgType);
	}
	
	

	
}
