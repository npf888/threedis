package com.three.redis.subscribe.changedatabasedispatcher.every;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.globals.Globals;
import com.three.redis.RedisCacheManager;

@Component
public class RedisChangeGift {

	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	public void execute(String className,String id){
//		Globals.getPersistService().persist(base);
	}
}
