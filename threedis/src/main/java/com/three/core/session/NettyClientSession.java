package com.three.core.session;

import io.netty.channel.ChannelHandlerContext;

import com.three.player.playerObj.Player;

/**
 * �û�������Ϸ֮�� ���� ��session
 */
public class NettyClientSession {

	
	private ChannelHandlerContext ctx = null;
	private Player player;
	
	public NettyClientSession(ChannelHandlerContext ctx){
		this.ctx=ctx;
	}
	
	
	
	
	public String getClientIp(){
		return this.ctx.channel().remoteAddress().toString();
	}




	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
}