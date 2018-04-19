package com.three.player.msg;

import com.three.core.msg.mtype.perType.PlayerType;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.player.playerObj.Player;

/**
 * 登录 第一次
 * @author JavaServer
 *
 */
public class CGLoginIn extends BaseMessageJSON{

	public CGLoginIn() {
		super(PlayerType.CG_LOGIN_IN);
	}

	
	@Override
	public void execute(){
		
		/**
		 * 创建用户
		 */
		
		Player player = new Player();
		this.getNettyClientSession().setPlayer(player);
	}
}
