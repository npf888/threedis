package com.three.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.gift.redis.RedisGiftService;
import com.three.globals.InitService;
import com.three.player.redis.RedisUserInfoService;

/**
 * 所有和 redis相关的 Service 都在这里边
 * @author JavaServer
 *
 */
@Service
public class RedisService implements InitService{

	
	@Autowired
	private  RedisUserInfoService redisUserInfoService;
	@Autowired
	private  RedisGiftService redisGiftService;
	
	@Override
	public void init() {
		redisGiftService.init();
		
	}

	
	public RedisUserInfoService getRedisUserInfoService() {
		return redisUserInfoService;
	}

	public RedisGiftService getRedisGiftService() {
		return redisGiftService;
	}
	
	
	

}
