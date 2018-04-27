package com.three.gift.redis;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.PersistanceObject;
import com.three.gift.db.Gift;
import com.three.gift.db.entity.PGift;
import com.three.player.db.Human;
import com.three.redis.RedisCacheManager;
import com.three.redis.RedisEnum;
import com.three.redis.inter.RedisService;

@Service
public class RedisGiftService implements RedisService{

	Logger logger = Logger.getLogger(RedisGiftService.class);
	@Autowired
	private  RedisCacheManager redisCacheManager;
	
	
	@Override
	public void init() {
		//这步 的目的是为了 看看 game_server 启动的时候  验证  redis 有没有启动,没有启动 就会报错
		redisCacheManager.get("ok");
		logger.info("redis started ok");
	}

	
	
	/**
	 * 把 数据库中的  gift 放到 redis 中
	 */
	public void setDataFromDatabaseToRedis(String playerCharId,List<PGift> pgiftList) {
		redisCacheManager.hset(RedisEnum.GIFT.getName(), playerCharId, pgiftList);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PGift> getGift(String playerCharId){
		List<PGift> pgiftList = (List<PGift>)redisCacheManager.hget(RedisEnum.GIFT.getName(), playerCharId);
		return pgiftList;
		
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updateRedis(Human human,PersistanceObject base) {
		Gift gift = (Gift)base;
		Object obj = redisCacheManager.hget(RedisEnum.GIFT.getName(), human.getCharId());
		if(obj == null){//第一次 插入一条数据
			List<PGift> giftList = new ArrayList<PGift>();
			giftList.add(gift.toEntity());
			redisCacheManager.hset(RedisEnum.GIFT.getName(), human.getCharId(), giftList);
		}else{//更新一条数据
			List<PGift> giftList = (List<PGift>)obj;
			List<PGift> existList = new ArrayList<PGift>();
			for(PGift pGift:giftList){
				if(pGift.getId().longValue() == gift.getId().longValue()){
					existList.add(pGift);
				}
			}
			giftList.removeAll(existList);
			giftList.add(gift.toEntity());
			redisCacheManager.hset(RedisEnum.GIFT.getName(), human.getCharId(), giftList);
			
			
		}
		
	}
}
