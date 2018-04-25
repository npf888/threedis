package com.three.player.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.three.globals.InitService;
import com.three.player.playerObj.Player;

/**
 * 在线用户的service
 * @author JavaServer
 *
 */
@Service
public class OnLineService implements InitService{

	
	private Map<Integer,Player> playerMap = new HashMap<Integer,Player>();
	
	
	
	
	@Override
	public void init() {
		
	}
	
	
	//添加在线人数 到内存中
	public void setOnlinePlayer(Player player){
		playerMap.put(player.getHuman().getPassportId(), player);
	}
	
	

}
