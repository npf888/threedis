package com.three.gift.db.entity;

import com.three.database.inter.BaseEntity;

/**
 * 对应数据库 实体  字段一一对应
 * @author JavaServer
 *
 */
public class GiftEntity implements BaseEntity{
	
	
	private Integer id;

	
	
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id=id;
	}

	
	
	
	
	

}
