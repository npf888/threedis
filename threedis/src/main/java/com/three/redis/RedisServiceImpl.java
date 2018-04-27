package com.three.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.PersistanceObject;
import com.three.gift.db.Gift;
import com.three.gift.redis.RedisGiftService;
import com.three.player.db.Human;
import com.three.player.redis.RedisUserInfoService;
import com.three.redis.channel.RedisChannel;
import com.three.redis.inter.RedisService;

/**
 * 所有和 redis相关的 Service 都在这里边
 * @author JavaServer
 *
 */
@Service
public class RedisServiceImpl implements RedisService{

	
	
	
	@Autowired
	private RedisEntityMapper redisEntityMapper;
	
	@Override
	public void init() {
		redisEntityMapper.init();
	}

	
	public RedisUserInfoService getRedisUserInfoService() {
		return (RedisUserInfoService)redisEntityMapper.getRedisClazz(Human.class);
	}

	public RedisGiftService getRedisGiftService() {
		return (RedisGiftService)redisEntityMapper.getRedisClazz(Gift.class);
	}
	
	


	/**
	 * update redis 中的数据对象( 类似于 分发器)
	 */
	@Override
	public void updateRedis(Human human, PersistanceObject<?> base) {
		//先更新redis
		RedisService redisService = this.redisEntityMapper.getRedisClazz(base.getClass());
		redisService.updateRedis(human, base);
		//再告诉redis去 更新数据库
		String message = base.getClass().getSimpleName()+":"+base.getId();
		getRedisUserInfoService().publish(RedisChannel.change_datebase, message);
	}



}
