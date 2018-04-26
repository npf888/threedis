package com.three.gift.db.entity;

import com.three.database.inter.BaseEntity;

/**
 * 对应数据库 实体  字段一一对应
 * @author JavaServer
 *
 */
public class PGift implements BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4554465270167466973L;
	
	private Long id;
	private String name;
	private Long humanId;
	private String charId;
	
	
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Long getHumanId() {
		return humanId;
	}
	public void setHumanId(Long humanId) {
		this.humanId = humanId;
	}

	@Override
	public String getCharId() {
		return charId;
	}
	@Override
	public void setCharId(String charId) {
		this.charId = charId;
	}

	
	
	
	
	

}
