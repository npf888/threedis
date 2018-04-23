package com.three.core.session;

import io.netty.channel.ChannelHandlerContext;

import com.three.core.msg.inter.IMessage;
import com.three.core.protobuf.ProtobufTransform;
import com.three.globals.Globals;
import com.three.player.playerObj.Player;

/**
 * 用户进入游戏之后 创建 的session
 */
public class NettyClientSession {

	
	private ChannelHandlerContext ctx = null;
	private Player player;
	private Globals globals;
	
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
	
	public Globals getGlobals() {
		return globals;
	}
	public void setGlobals(Globals globals) {
		this.globals = globals;
	}




	public void sendMessageToCtx(IMessage msg){
		ctx.writeAndFlush(globals.getMessageRecognizer().getProtobufTransform().toWriteMsg(msg));
	}
	
	
}
