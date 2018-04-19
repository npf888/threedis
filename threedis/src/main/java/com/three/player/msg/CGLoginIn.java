package com.three.player.msg;

import com.three.core.msg.mtype.perType.PlayerType;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.player.playerObj.Player;

/**
 * ��¼ ��һ��
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
		 * �����û�
		 */
		
		Player player = new Player();
		this.getNettyClientSession().setPlayer(player);
	}
}
