package com.three.gift.manager;

import java.util.ArrayList;
import java.util.List;

import com.three.database.IDService.PerType;
import com.three.gift.db.Gift;
import com.three.gift.db.entity.PGift;
import com.three.gift.db.service.GiftDBService;
import com.three.globals.Globals;
import com.three.globals.ManagerOfHuman;
import com.three.player.db.Human;

public class GiftManager implements ManagerOfHuman{

	private Human owner;
	private List<Gift> gifts;
	
	public GiftManager(Human human) {
		this.owner=human;
	}

	@Override
	public void load() {
		gifts = new ArrayList<Gift>();
		//根据 human的 status 判断 是否 取 redis 取数据
		List<PGift> rPgiftList = null;
		if(owner.getStatus().intValue() == Human.HUMAN_IN){//说明 一直 在redis 中，所以 直接去redis 加载数据
			rPgiftList = Globals.getRedisService().getRedisGiftService().getGift(owner.getCharId());
			
		}else if(owner.getStatus().intValue() == Human.HUMAN_NEW){
			GiftDBService dbService = (GiftDBService)Globals.getPersistService().getDBService(Gift.class);
			/**
			 * 下边 不是测试的 
			 */
			
			PGift condition = new PGift();
			condition.setHumanId(owner.getId());
			List<PGift> pgiftList = dbService.findAllByCondition(condition);
			/*if(pgiftList == null || pgiftList.size() == 0){
				// 测试用的先 初始化几条数据， 以后再注释掉
				for(int i=0;i<10;i++){
					PGift pGift = new PGift();
					int num = dbService.getNum();
					pGift.setCharId(Globals.getiDService().getCharId(PGift.class, num));
					pGift.setHumanId(owner.getId());
					pGift.setName("name:"+(i+1));
					dbService.create(pGift);
					
				}
				pgiftList = dbService.findAllByCondition(condition);
			}*/
			if(pgiftList != null && pgiftList.size() > 0){
				Globals.getRedisService().getRedisGiftService().setDataFromDatabaseToRedis(owner.getCharId(),pgiftList);
				rPgiftList = Globals.getRedisService().getRedisGiftService().getGift(owner.getCharId());
			}
		}
		//转换
		if(rPgiftList != null && rPgiftList.size()>0 ){
			for(PGift entity:rPgiftList){
				Gift gift = new Gift();
				gift.fromEntity(entity);
				gifts.add(gift);
			}
		}
		
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
	
	
	
	
	

}
