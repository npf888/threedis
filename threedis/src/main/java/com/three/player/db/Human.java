package com.three.player.db;

import com.three.database.inter.PersistanceObject;
import com.three.globals.Globals;
import com.three.player.db.entity.PHuman;
import com.three.player.service.HumanManagerService;

public class Human implements PersistanceObject<PHuman>{

	
	public static final int HUMAN_NEW=0;
	public static final int HUMAN_IN=1;

	
	private Long id;
	//不能修改
	private String deviceMac;
	//不能修改
	private String charId;

	private String nickname;
	
	private Integer status;//状态 ，  0：刚加载进内存，1：还没有从内存中退出(还存在redis中)
	
	
	
	//用户 所有 的manager 的 服务
	private HumanManagerService humanManagerService;
	
	
	public void init(){
		humanManagerService = new HumanManagerService();
		humanManagerService.init(this);
		humanManagerService.load();
	}
	
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
	
	public String getCharId() {
		return this.charId;
	}
	
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

	
	
	@Override
	public void fromEntity(PHuman entity) {
		this.setId(entity.getId());
		this.setDeviceMac(entity.getDeviceMac());
		this.setCharId(entity.getCharId());
		this.setStatus(entity.getStatus());
		this.setNickname(entity.getNickname());
	}
	@Override
	public PHuman toEntity() {
		PHuman entity = new PHuman();
		entity.setId(this.getId());
		entity.setDeviceMac(this.getDeviceMac());
		entity.setCharId(this.getCharId());
		entity.setStatus(this.getStatus());
		entity.setNickname(this.getNickname());
		return entity;
	}

	
	@Override
	public void setModified(Human human) {
		Globals.getRedisService().updateRedis(human, this);
		
	}


	
}
