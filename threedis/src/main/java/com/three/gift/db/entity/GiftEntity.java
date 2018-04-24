package com.three.gift.db.entity;

import com.three.database.inter.BaseEntity;

/**
 * 对应数据库 实体  字段一一对应
 * @author JavaServer
 *
 */
public class GiftEntity implements BaseEntity{
	
	
	private int id;

	
	
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id=id;
	}

	
	
	
	
	

}
