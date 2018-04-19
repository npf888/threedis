package com.three.core.handler;

import com.three.core.msg.inter.IMessage;
import com.three.player.playerObj.Player;

public interface MessagerHandler {

	void execute(Player player,IMessage curMessage);
}
