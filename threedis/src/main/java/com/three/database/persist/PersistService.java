package com.three.database.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;
import com.three.globals.Globals;
import com.three.globals.InitService;
import com.three.player.db.Human;
import com.three.player.db.entity.PHuman;

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
	public void persist(PersistanceObject<?> base) {
		this.persistentThread.persist(base);
	}
	
	public DBService getDBService(Class<?> clazz){
		return entityServiceMapper.getClassDBServiceMapByClass(clazz);
	}
	
	public Human initHuman(String deviceMac){
		PHuman humanEntity = (PHuman)this.getDBService(Human.class).findByDeviceMac(deviceMac);
		Human human = new Human();
		if(humanEntity == null){//等于空 就去创建用户 ，然后进入游戏
			human.setDeviceMac(deviceMac);
			human.setModified();
		}else{//不等于空 就进入游戏
			human.fromEntity(humanEntity);
		}
		human.init();//初始化 human 中的 manager
		
		return human;
	}
	
}
