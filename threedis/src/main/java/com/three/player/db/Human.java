package com.three.player.db;

import com.three.database.inter.PersistanceObject;
import com.three.globals.Globals;
import com.three.player.db.entity.PHuman;
import com.three.player.service.HumanManagerService;

public class Human implements PersistanceObject<PHuman>{

	
	public static final int HUMAN_NEW=0;
	public static final int HUMAN_IN=1;

	
	private Long id;
	private Integer passportId;
	private String deviceMac;
	private String charId;
	
	private Integer status;//状态 不存入数据库，  0：刚加载进内存，1：还没有从内存中退出
	
	
	
	//用户 所有 的manager 的 服务
	private HumanManagerService humanManagerService;
	
	
	public void init(){
		humanManagerService = new HumanManagerService();
		humanManagerService.init(this);
		humanManagerService.load();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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


	@Override
	public void fromEntity(PHuman entity) {
		this.setId(entity.getId());
		this.setDeviceMac(entity.getDeviceMac());
		this.setCharId(entity.getCharId());
		this.setPassportId(entity.getPassportId());
	}
	@Override
	public PHuman toEntity() {
		PHuman entity = new PHuman();
		entity.setId(this.getId());
		entity.setDeviceMac(this.deviceMac);
		entity.setCharId(this.charId);
		entity.setPassportId(this.passportId);
		return entity;
	}

	@Override
	public void setModified() {
		Globals.getPersistService().persist(this);
		
	}


	
}
