package com.three.player.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.PersistanceObject;
import com.three.player.db.Human;
import com.three.player.db.entity.PHuman;
import com.three.redis.inter.RedisService;

/**
 * 负责处理 用户所属的表 的信息
 * @author JavaServer
 *
 */
@Service 
public class RedisUserInfoService implements RedisService{

	
	
	@Autowired
	private RedisHumanService redisHumanService;
	
	
	@Override
	public void init() {
		
	}
	
	/**
	 * 从redis中 加载 用户信息
	 * @param deviceMac
	 * @return
	 */
	public Human initUserInfo(String deviceMac) {
		//先初始化 一下用户的所有的信息 （加载到redis）
		PHuman pHuman = redisHumanService.initUserInfo(deviceMac);
		//从redis 中 加载用户信息
		Human human = new Human();
		human.fromEntity(pHuman);
		//初始化 human 中的 manager（相关的表 加载到redis）
		human.init();
		return human;
	}
	
	
	public void publish(String channel,String message){
		redisHumanService.publish(channel,message);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateRedis(Human human, PersistanceObject base) {
		redisHumanService.update(human);
		
	}
	
	
	
	

	
}
