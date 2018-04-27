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
	private String deviceMac;
	private String charId;
	private String nickname;
	private Integer status;//状态 ，  0：刚加载进内存，1：还没有从内存中退出(还存在redis中)
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id=id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	

}
