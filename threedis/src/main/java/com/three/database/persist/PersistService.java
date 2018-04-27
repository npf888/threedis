package com.three.database.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.globals.InitService;

/**
 * 处理db这块的总的 service
 * @author JavaServer
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class PersistService implements InitService{

	//数据库处理的线程
	@Autowired
	private  PersistentThread persistentThread;
	@Autowired
	private  EntityServiceMapper entityServiceMapper;
	
	
	@Override
	public void init(){
		persistentThread.init();
		entityServiceMapper.init();
	}
	
	
	


	/**
	 * 持久化 实体
	 * @param base
	 */
	public void persist(BaseEntity base) {
		this.persistentThread.persist(base);
	}
	
	
	public DBService getDBService(Class<?> clazz){
		return entityServiceMapper.getClassDBServiceMapByClass(clazz);
	}
	
	
}
