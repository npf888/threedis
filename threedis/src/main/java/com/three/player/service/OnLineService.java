package com.three.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.globals.InitService;
import com.three.player.playerObj.Player;
import com.three.redis.RedisCacheManager;
import com.three.redis.RedisEnum;

/**
 * 在线用户的service
 * @author JavaServer
 *
 */
@Service
public class OnLineService implements InitService{

	@Autowired
	private  RedisCacheManager redisCacheManager;
	
	
	@Override
	public void init() {
		
	}
	
	/**
	 * 把 在线用户 放到 redis 
	 */
	public void setOnlinePlayer(Player player){
		redisCacheManager.hset(RedisEnum.ONLINE_PLAYER.toString(), player.getHuman().getId()+"", player);
	}

	/**
	 * 从 redis 中获取在线用户
	 */
	public Player getOnlinePlayer(String playerId){
		Object obj = redisCacheManager.hget(RedisEnum.ONLINE_PLAYER.toString(), playerId);
		if(obj != null){
			return (Player)obj;
		}
		return null;
	}
}
