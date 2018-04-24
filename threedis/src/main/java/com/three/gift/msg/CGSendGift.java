package com.three.gift.msg;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mcode.percode.GiftCode;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.gift.handler.GiftHandlerFactory;
import com.three.player.playerObj.Player;

/**
 * 
 * 发送礼物
 * @author JavaServer
 *
 */
public class CGSendGift extends BaseMessageJSON{


	public CGSendGift(){
		super(GiftCode.CG_SEND_GIFT,IMessage.CG_MSG_TYPE);
	}

	
	
	@Override
	public void execute() {
		GiftHandlerFactory.getHandler().execute(this.getNettyClientSession().getPlayer(), this);
	}
	

	
}
