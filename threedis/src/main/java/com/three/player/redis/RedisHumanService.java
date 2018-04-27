package com.three.player.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.three.database.IDService.PerType;
import com.three.globals.Globals;
import com.three.player.db.Human;
import com.three.player.db.entity.PHuman;
import com.three.player.db.service.HumanDBService;
import com.three.redis.RedisCacheManager;
import com.three.redis.RedisEnum;

/**
 * redis中专门处理 human的服务
 * @author JavaServer
 *
 */
@Service
public class RedisHumanService {

	@Autowired
	private  RedisCacheManager redisCacheManager;


	/**
	 * 初始化 userInfo
	 * @param deviceMac
	 * @return
	 */
	public PHuman initUserInfo(String deviceMac) {
		//先从数据库 中查出用户
		HumanDBService dbService = (HumanDBService)Globals.getPersistService().getDBService(PHuman.class);
		PHuman humanEntity = (PHuman)dbService.findByDeviceMac(deviceMac);;
		if(humanEntity == null){//等于空 说明是第一次登录， 就去创建用户 ，然后进入游戏
			humanEntity = createPHuman(deviceMac);
		}else{
			//先看看 redis 中是否存在
			String key = RedisEnum.HUMAN.toString();
			//先判断 是否存在
			boolean has = redisCacheManager.hHasKey(key, humanEntity.getCharId());
			if(has){
				Object rObj = redisCacheManager.hget(key,humanEntity.getCharId());
				if(rObj != null){
					PHuman phuman = (PHuman)rObj;
					phuman.setStatus(Human.HUMAN_IN);
					//更新状态
					redisCacheManager.hset(RedisEnum.HUMAN.toString(), humanEntity.getCharId(),phuman);
					return phuman;
				}
			}
		}
		//加载到 redis
		redisCacheManager.hset(RedisEnum.HUMAN.toString(), humanEntity.getCharId(),humanEntity);
		//再从 redis 中 查出来（主要是 验证冲redis中 查出来 对不对）
		PHuman rPHuman = (PHuman)redisCacheManager.hget(RedisEnum.HUMAN.toString(), humanEntity.getCharId());
		return rPHuman;
	}
	

	/**
	 * 创建 （用户第一次登录）
	 * 
	 * 用户(以及和用户相关的所有的 实体类)
	 * @param deviceMac
	 * @return
	 */
	@Transactional
	private PHuman createPHuman(String deviceMac){
		PHuman humanEntity = new PHuman();
		humanEntity.setDeviceMac(deviceMac);
		humanEntity.setStatus(Human.HUMAN_NEW);
		HumanDBService dbService = (HumanDBService)Globals.getPersistService().getDBService(PHuman.class);
		Long id = dbService.create(humanEntity);
		humanEntity.setId(id);
		humanEntity.setCharId(Globals.getiDService().getCharId(PHuman.class, id.intValue()));
		dbService.update(humanEntity);
		return humanEntity;
	}


	public void update(Human human) {
		redisCacheManager.hset(RedisEnum.HUMAN.getName(), human.getCharId(), human.toEntity());
	}


	public void publish(String listenerChannel, String message) {
		redisCacheManager.publish(listenerChannel, message);
	}
	
}
