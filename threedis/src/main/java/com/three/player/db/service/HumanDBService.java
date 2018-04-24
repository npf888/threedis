package com.three.player.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;
import com.three.player.db.Human;
import com.three.player.db.dao.HumanDao;
import com.three.player.db.entity.PHuman;

@SuppressWarnings("unchecked")
@Service
public class HumanDBService implements DBService{

	@Autowired
	private HumanDao humanDao;
	
	@Override
	public void saveOrUpdate(PersistanceObject base) {
		Human  human = (Human)base;
		if(human.getId() == null || human.getId() == 0){ 
			humanDao.insert(human.toEntity());
		}else{
			humanDao.update(human.toEntity());
		}
	}

	@Override
	public PHuman findById(int id) {
		return humanDao.getById(id);
	}

	@Override
	public PHuman findByDeviceMac(String deviceMac) {
		PHuman param = new PHuman();
		param.setDeviceMac(deviceMac);
		List<PHuman> entityList =  (List<PHuman>)humanDao.queryList(param);
		if(entityList != null && entityList.size()>0){
			return entityList.get(0);
		}
		return null;
	}

	
}
