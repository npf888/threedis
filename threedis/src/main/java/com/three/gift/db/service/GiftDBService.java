package com.three.gift.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.database.inter.PersistanceObject;
import com.three.gift.db.Gift;
import com.three.gift.db.dao.GiftDao;
import com.three.gift.db.entity.PGift;

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
	public void saveOrUpdate(PersistanceObject base) {
		Gift gift = (Gift)base.toEntity();
		
		if(gift == null || gift.getId() == 0){
			giftDao.insert(gift.toEntity());
		}else{
			giftDao.update(gift.toEntity());
		}
	}


	@Override
	public PGift findById(int id) {
		return giftDao.getById(id);
	}

	
	public List<PGift> findAllByCondition(PGift condition){
		return (List<PGift>)giftDao.queryList(condition);
	}


	@Override
	public int getNum() {
		PGift entity = new PGift();
		return giftDao.queryCount(entity);
	}


	@Override
	public void create(BaseEntity entity) {
		giftDao.insert((PGift)entity);
		
	}


	
}
