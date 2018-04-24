package com.three.database.map;

import java.util.HashMap;
import java.util.Map;

import com.three.database.inter.DBService;
import com.three.gift.db.Gift;
import com.three.gift.db.service.GiftDBService;

/**
 * 建立实体 和  dbService 的关系
 * @author JavaServer
 *
 */
public class EntityServiceMapper {

	private static Map<Class<?>,DBService> classDBServiceMap  = new HashMap<Class<?>,DBService>();
	static{
		classDBServiceMap.put(Gift.class,new GiftDBService());
		
	}
	
	
	
	public static DBService getClassDBServiceMapByClass(Class<?> clazz){
		return classDBServiceMap.get(clazz);
	}
}
