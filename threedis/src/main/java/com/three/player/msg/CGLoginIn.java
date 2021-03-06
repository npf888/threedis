package com.three.player.msg;

import org.apache.commons.lang3.StringUtils;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mcode.percode.PlayerCode;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.globals.Globals;
import com.three.player.db.Human;
import com.three.player.playerObj.Player;

/**
 * 用户登录
 * @author JavaServer
 *
 */
public class CGLoginIn extends BaseMessageJSON{

	private String deviceMac;
	
	
	public CGLoginIn() {
		super(PlayerCode.CG_LOGIN_IN,IMessage.CG_MSG_TYPE);
	}

	
	
	
	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}





	@Override
	public void execute(){
		
		/**
		 * 创建用户
		 */
		Player player = new Player();
		player.setNettyClientSession(this.getNettyClientSession());
		
		if(StringUtils.isBlank(deviceMac)){
//			player.sendMessage(msg);//提示 deviceMac 为空
			return;
		}
		//先初始化 到 redis里
		Human human =Globals.getRedisService().getRedisUserInfoService().initUserInfo(deviceMac);
		player.setHuman(human);
		
		human.setNickname("呼啦啦啦");
		
		human.setModified(human);
		this.getNettyClientSession().setPlayer(player);
		
		//再放到 onlineService 中
		Globals.getOnLineService().setOnlinePlayer(player);
		
		System.out.println(player);
	}
}
