package com.three.database.persist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.database.inter.DBService;
import com.three.gift.db.entity.PGift;
import com.three.gift.db.service.GiftDBService;
import com.three.globals.InitService;
import com.three.player.db.entity.PHuman;
import com.three.player.db.entity.PTest;
import com.three.player.db.service.HumanDBService;
import com.three.player.db.service.PTestDBService;

/**
 * 建立实体 和  dbService 的关系
 * @author JavaServer
 *
 */
@SuppressWarnings("rawtypes")
@Component
public class EntityServiceMapper  implements InitService{

	private  Map<Class<?>,DBService> classDBServiceMap  = new HashMap<Class<?>,DBService>();
	
	@Autowired
	private HumanDBService humanDBService;
	@Autowired
	private GiftDBService giftDBService;
	@Autowired
	private PTestDBService pTestDBService;
	
	
	@Override
	public void init(){
		classDBServiceMap.put(PGift.class,giftDBService);
		classDBServiceMap.put(PHuman.class,humanDBService);
		classDBServiceMap.put(PTest.class,pTestDBService);
		
	}
	
	
	
	public  DBService getClassDBServiceMapByClass(Class<?> clazz){
		DBService dbService =  classDBServiceMap.get(clazz);
		return dbService;
	}
}
