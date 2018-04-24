package com.three.database.persist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.three.database.inter.DBService;
import com.three.gift.db.Gift;
import com.three.gift.db.service.GiftDBService;
import com.three.player.db.Human;
import com.three.player.db.service.HumanDBService;

/**
 * 建立实体 和  dbService 的关系
 * @author JavaServer
 *
 */
@Component
public class EntityServiceMapper {

	private static Map<Class<?>,DBService> classDBServiceMap  = new HashMap<Class<?>,DBService>();
	static{
		classDBServiceMap.put(Gift.class,new GiftDBService());
		classDBServiceMap.put(Human.class,new HumanDBService());
		
	}
	
	
	
	public static DBService getClassDBServiceMapByClass(Class<?> clazz){
		return classDBServiceMap.get(clazz);
	}
}
