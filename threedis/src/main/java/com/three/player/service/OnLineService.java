package com.three.player.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.three.globals.InitService;
import com.three.player.db.Human;
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

	
	private Map<Integer,Player> playerMap = new HashMap<Integer,Player>();
	
	@Autowired
	private  RedisCacheManager redisCacheManager;
	
	
	@Override
	public void init() {
		
	}
	
	/**
	 * 把 在线用户 放到 redis 
	 */
	public void setOnlinePlayer(Player player){
		playerMap.put(player.getHuman().getId(), player);
		redisCacheManager.hset(RedisEnum.ONLINE_PLAYER.toString(), player.getHuman().getId()+"", JSON.toJSONString(player.getHuman()));
	}

	/**
	 * 从 redis 中获取在线用户
	 */
	public Player getOnlinePlayer(String playerId){
		Object obj = redisCacheManager.hget(RedisEnum.ONLINE_PLAYER.toString(), playerId);
		if(obj != null){
			String jsonPlayer = (String)obj;
			Human human =  JSON.parseObject(jsonPlayer, Human.class);
			Player player = playerMap.get(playerId);
			player.setHuman(human);
			return player;
		}
		return null;
	}
}
