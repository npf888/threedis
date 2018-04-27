package com.three.redis.inter;

import com.three.database.inter.PersistanceObject;
import com.three.player.db.Human;


/**
 * redis service 的接口
 * @author JavaServer
 *
 */
public interface RedisService {

	
	public void init();
	
	public void updateRedis(Human human,PersistanceObject<?> base);
	
}
