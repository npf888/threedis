package com.three.gift.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.three.database.inter.BaseEntity;
import com.three.database.inter.DBService;
import com.three.gift.db.dao.GiftDao;
import com.three.gift.db.entity.PGift;

/**
 * 礼物的数据库服务
 * @author JavaServer
 *
 */
@SuppressWarnings("rawtypes")
@Service
public class GiftDBService implements DBService{
	
	@Autowired
	private GiftDao giftDao;



	@Override
	public void update(BaseEntity base) {
		giftDao.update((PGift)base);
	}


	@Override
	public PGift findById(int id) {
		return giftDao.getById(id);
	}

	
	
	@SuppressWarnings("unchecked")
	public List<PGift> findAllByCondition(PGift condition){
		return (List<PGift>)giftDao.queryList(condition);
	}


	@Override
	public int getNum() {
		PGift entity = new PGift();
		return giftDao.queryCount(entity);
	}


	@Override
	public Long create(BaseEntity entity) {
		return giftDao.insert((PGift)entity);
		
	}


	
}
