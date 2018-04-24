package com.three.player.db;

import com.three.database.inter.PersistanceObject;
import com.three.globals.Globals;
import com.three.player.db.entity.PHuman;
import com.three.player.service.HumanManagerService;

public class Human implements PersistanceObject<PHuman>{

	
	

	
	private Integer id;
	
	private String deviceMac;
	
	
	//用户 所有 的manager 的 服务
	private HumanManagerService humanManagerService;
	
	
	public void init(){
		humanManagerService = new HumanManagerService();
		humanManagerService.init(this);
		humanManagerService.load();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
	
	
	
	@Override
	public void fromEntity(PHuman entity) {
		this.setId(entity.getId());
		this.setDeviceMac(entity.getDeviceMac());
	}
	@Override
	public PHuman toEntity() {
		PHuman entity = new PHuman();
		entity.setId(this.getId());
		entity.setDeviceMac(deviceMac);
		return entity;
	}

	@Override
	public void setModified() {
		Globals.getPersistService().persist(this);
		
	}
}
