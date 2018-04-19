package com.three.gift.msg;

import com.three.core.msg.mtype.perType.GiftType;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.gift.handler.GiftHandlerFactory;
import com.three.player.playerObj.Player;

/**
 * 
 * ∑¢ÀÕ¿ÒŒÔ
 * @author JavaServer
 *
 */
public class CGSendGift extends BaseMessageJSON{


	public CGSendGift(){
		super(GiftType.CG_SEND_GIFT);
	}

	
	
	@Override
	public void execute() {
		GiftHandlerFactory.getHandler().execute(new Player(), this);
	}
	

	
}
