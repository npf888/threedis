package com.three.database.persist;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;

/**
 * 异步操作数据库的线程
 * @author JavaServer
 *
 */
@Component
public class PersistentThread extends Thread{

	Logger logger = Logger.getLogger(PersistentThread.class);
	
	private BlockingQueue<PersistanceObject<?>> entityQueue = new LinkedBlockingDeque<PersistanceObject<?>>();
	
	
	public void init(){
		this.start();
	}
	
	
	@Override
	public void run(){
		
			
			try {
				while(true){
					PersistanceObject<?> base = entityQueue.poll();
					if(base != null){
						DBService dbService = EntityServiceMapper.getClassDBServiceMapByClass(base.getClass());
						if(dbService != null){
							dbService.saveOrUpdate(base);
						}else{
							logger.info("当前"+base.getClass()+": 对应的 DBService 不存在");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	
	public void persist(PersistanceObject<?> base){
		boolean successful = entityQueue.offer(base);
		if(!successful){
			logger.info("当前"+base.getClass()+": 插入队列的 将要保存到数据库的对象 没有保存进去 ，请查看");
		}
	}
}