package com.three.player.db.entity;

import com.three.database.inter.BaseEntity;
/**
 * 持久化 的 实体类
 * @author JavaServer
 *
 */
public class PHuman implements BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1132393016628107653L;
	
	
	private Long id;
	private Integer passportId;
	private String deviceMac;
	private String charId;
	
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
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
