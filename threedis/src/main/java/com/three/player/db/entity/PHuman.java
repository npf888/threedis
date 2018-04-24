package com.three.player.db.entity;

import com.three.database.inter.BaseEntity;
/**
 * 持久化 的 实体类
 * @author JavaServer
 *
 */
public class PHuman implements BaseEntity{

	private Integer id;
	private String deviceMac;
	
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id=id;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
	
	
	

}
