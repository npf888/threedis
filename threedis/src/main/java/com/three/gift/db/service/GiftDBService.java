package com.three.gift.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;
import com.three.gift.db.dao.GiftDao;
import com.three.gift.db.entity.GiftEntity;

/**
 * 礼物的数据库服务
 * @author JavaServer
 *
 */
@Service
public class GiftDBService implements DBService{
	
	@Autowired
	private GiftDao giftDao;




	@Override
	public void saveOrUpdate(PersistanceObject<BaseEntity> base) {
		GiftEntity entity = (GiftEntity)base.toEntity();
		giftDao.update(entity);
	}
	
}
