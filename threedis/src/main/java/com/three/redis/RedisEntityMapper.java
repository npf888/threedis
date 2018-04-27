package com.three.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.PersistanceObject;
import com.three.gift.db.Gift;
import com.three.gift.redis.RedisGiftService;
import com.three.globals.InitService;
import com.three.player.db.Human;
import com.three.player.playerObj.Player;
import com.three.player.redis.RedisUserInfoService;
import com.three.redis.inter.RedisService;

/**
 * 
 * 内存中的对象 和redis的映射
 * @author JavaServer
 *
 */
@Component
public class RedisEntityMapper {

	@Autowired
	private  RedisUserInfoService redisUserInfoService;
	@Autowired
	private  RedisGiftService redisGiftService;
	
	
	
	/**
	 * 所有redis 的 service 的映射
	 */
	private Map<Class<?>,RedisService> mapper = new HashMap<Class<?>,RedisService>();
	
	public void init(){
		redisGiftService.init();
		
		//加载映射
		mapper.put(Human.class, redisUserInfoService);
		mapper.put(Gift.class, redisGiftService);
		
	}
	
	
	public RedisService getRedisClazz(Class<?> base){
		RedisService redisService =  mapper.get(base);
		return redisService;
	}

}
