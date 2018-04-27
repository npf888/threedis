package com.three.redis.subscribe.changedatabasedispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.gift.db.Gift;
import com.three.player.db.Human;
import com.three.redis.subscribe.changedatabasedispatcher.every.RedisChangeGift;
import com.three.redis.subscribe.changedatabasedispatcher.every.RedisChangeHuman;

@Component
public class RedisChangeDatabaseDispatcher {

	@Autowired
	private RedisChangeHuman redisChangeHuman;
	@Autowired
	private RedisChangeGift redisChangeGift;

	public void dispatcher(String className, String id) {
		//分发
		if(className.equals(Human.class.getSimpleName())){
			redisChangeHuman.execute(className,id);
		}else if(className.equals(Gift.class.getSimpleName())){
			redisChangeGift.execute(className, id);
		}
		
	}
	
	
	
	
}
