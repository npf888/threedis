package com.three.player.msg;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mcode.percode.PlayerCode;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.player.playerObj.Player;

/**
 * 登录 第一次
 * @author JavaServer
 *
 */
public class CGLoginIn extends BaseMessageJSON{

	public CGLoginIn() {
		super(PlayerCode.CG_LOGIN_IN,IMessage.CG_MSG_TYPE);
	}

	
	@Override
	public void execute(){
		
		/**
		 * 创建用户
		 */
		
		Player player = new Player();
		player.setNettyClientSession(this.getNettyClientSession());
		this.getNettyClientSession().setPlayer(player);
	}
}
