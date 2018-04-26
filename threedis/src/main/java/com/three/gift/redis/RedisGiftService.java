package com.three.gift.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.gift.db.entity.PGift;
import com.three.globals.InitService;
import com.three.player.playerObj.Player;
import com.three.redis.RedisCacheManager;
import com.three.redis.RedisEnum;

@Service
public class RedisGiftService implements InitService{

	
	@Autowired
	private  RedisCacheManager redisCacheManager;
	
	
	@Override
	public void init() {
		//这步 的目的是为了 看看 game_server 启动的时候  验证  redis 有没有启动,没有启动 就会报错
		Object obj = redisCacheManager.get("ok");
		
	}

	
	
	/**
	 * 把 数据库中的  gift 放到 redis 中
	 */
	public void setDataFromDatabaseToRedis(String playerCharId,List<PGift> pgiftList) {
		redisCacheManager.hset(RedisEnum.GIFT.getName(), playerCharId, pgiftList);
	}
	
	
	public List<PGift> getGift(String playerCharId){
		List<PGift> pgiftList = (List<PGift>)redisCacheManager.hget(RedisEnum.GIFT.getName(), playerCharId);
		return pgiftList;
		
	}
}
