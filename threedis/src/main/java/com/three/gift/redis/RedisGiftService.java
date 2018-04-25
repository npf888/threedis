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
