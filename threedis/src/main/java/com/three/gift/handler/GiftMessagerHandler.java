package com.three.gift.handler;

import org.apache.log4j.Logger;

import com.three.core.handler.MessagerHandler;
import com.three.core.msg.inter.IMessage;
import com.three.gift.msg.CGSendGift;
import com.three.player.playerObj.Player;

public class GiftMessagerHandler implements MessagerHandler{

	Logger logger = Logger.getLogger(GiftMessagerHandler.class);

	/**
	 * 每个handler的 统一入口
	 */
	@Override
	public void execute(Player player, IMessage curMessage) {
		boolean open = true;//在配置文件中 配置
		if(!open){//开关 如果 整个模块不用的话 就会关闭
			return;
		}
		
		if(curMessage instanceof CGSendGift){
			sendGift();
		}
		
	};



	private void sendGift(){
		logger.info("现在 正在调用 sendGift");
	};
}