package com.three.redis.subscribe.changedatabasedispatcher.every;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.globals.Globals;
import com.three.player.db.entity.PHuman;
import com.three.redis.RedisCacheManager;
import com.three.redis.RedisEnum;

@Component
public class RedisChangeHuman {

	
	@Autowired
	private RedisCacheManager redisCacheManager;
	
	public void execute(String className,String id){
		String charId = Globals.getiDService().getCharId(PHuman.class, Integer.valueOf(id));
		PHuman pHuman = (PHuman)redisCacheManager.hget(RedisEnum.HUMAN.getName(), charId);
		Globals.getPersistService().persist(pHuman);
	}
}
