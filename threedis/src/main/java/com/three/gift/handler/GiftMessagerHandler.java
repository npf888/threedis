package com.three.gift.handler;

import org.apache.log4j.Logger;

import com.three.core.handler.MessagerHandler;
import com.three.core.msg.inter.IMessage;
import com.three.gift.msg.CGSendGift;
import com.three.gift.msg.GCSendGift;
import com.three.player.playerObj.Player;

public class GiftMessagerHandler implements MessagerHandler{

	Logger logger = Logger.getLogger(GiftMessagerHandler.class);

	
	@Override
	public void execute(Player player, IMessage curMessage) {
		boolean open = true;
		if(!open){
			return;
		}
		
		if(curMessage instanceof CGSendGift){
			sendGift(player);
		}
		
	};



	private void sendGift(Player player){
		logger.info("发送 sendGift");
		GCSendGift gCSendGift = new GCSendGift();
		player.sendMessage(gCSendGift);
	};
}