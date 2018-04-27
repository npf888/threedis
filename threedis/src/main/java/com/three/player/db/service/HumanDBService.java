package com.three.player.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.player.db.dao.HumanDao;
import com.three.player.db.entity.PHuman;

@SuppressWarnings("unchecked")
@Service
public class HumanDBService implements DBService{

	@Autowired
	private HumanDao humanDao;
	
	//创建用户
	@Override
	public Long create(BaseEntity base) {
		Long id = humanDao.insert((PHuman)base);
		return id;
	}
	
	//更新
	@Override
	public void update(BaseEntity base){
		humanDao.update((PHuman)base);
	}

	@Override
	public PHuman findById(int id) {
		return humanDao.getById(id);
	}

	
	public PHuman findByDeviceMac(String deviceMac) {
		PHuman param = new PHuman();
		param.setDeviceMac(deviceMac);
		List<PHuman> entityList =  (List<PHuman>)humanDao.queryList(param);
		if(entityList != null && entityList.size()>0){
			return entityList.get(0);
		}
		return null;
	}
	
	@Override
	public int getNum(){
		PHuman entity = new PHuman();
		int num = humanDao.queryCount(entity);
		return num+1;
	}

	
}
