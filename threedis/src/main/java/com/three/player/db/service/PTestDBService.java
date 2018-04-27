package com.three.player.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;
import com.three.player.db.dao.PTestDao;
import com.three.player.db.entity.PHuman;
import com.three.player.db.entity.PTest;

@SuppressWarnings("rawtypes")
@Service
public class PTestDBService implements DBService{

	@Autowired
	private PTestDao pTestDao;
	
	@Override
	public void update(BaseEntity base) {
		
	}
	
	//创建用户
	@Override
	public Long create(BaseEntity base) {
		
		PTest pTest = new PTest();
		pTest.setCharId("PHuman:1");
		pTest.setName("555555");
		
		return pTestDao.insert(pTest);
	}

	@Override
	public PHuman findById(int id) {
		return null;
	}

	
	@Override
	public int getNum(){
		return 0;
	}

	
}
