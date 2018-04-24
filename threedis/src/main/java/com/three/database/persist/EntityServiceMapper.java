package com.three.database.persist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.three.database.inter.DBService;
import com.three.gift.db.Gift;
import com.three.gift.db.service.GiftDBService;
import com.three.globals.InitService;
import com.three.player.db.Human;
import com.three.player.db.service.HumanDBService;

/**
 * 建立实体 和  dbService 的关系
 * @author JavaServer
 *
 */
@Component
public class EntityServiceMapper  implements InitService{

	private  Map<Class<?>,DBService> classDBServiceMap  = new HashMap<Class<?>,DBService>();
	
	@Autowired
	private HumanDBService humanDBService;
	@Autowired
	private GiftDBService giftDBService;
	
	
	@Override
	public void init(){
		classDBServiceMap.put(Gift.class,giftDBService);
		classDBServiceMap.put(Human.class,humanDBService);
		
	}
	
	
	
	public  DBService getClassDBServiceMapByClass(Class<?> clazz){
		DBService dbService =  classDBServiceMap.get(clazz);
		return dbService;
	}
}
