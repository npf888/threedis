package com.three.database.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;
import com.three.globals.InitService;

/**
 * 处理db这块的总的 service
 * @author JavaServer
 *
 */
@Service
public class PersistService implements InitService{

	//数据库处理的线程
	@Autowired
	private  PersistentThread persistentThread;
	
	
	@Override
	public void init(){
		persistentThread.init();
	}
	
	
	public  PersistentThread getPersistentThread() {
		return persistentThread;
	}
	public  void setPersistentThread(PersistentThread persistentThread) {
		this.persistentThread = persistentThread;
	}


	/**
	 * 持久化 实体
	 * @param base
	 */
	public void persist(PersistanceObject<?> base) {
		this.persistentThread.persist(base);
	}
	
	public DBService getDBService(Class<?> clazz){
		return EntityServiceMapper.getClassDBServiceMapByClass(clazz);
	}
	
	
}
