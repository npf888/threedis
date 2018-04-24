package com.three.player.db;

import com.three.database.inter.PersistanceObject;
import com.three.globals.Globals;
import com.three.player.db.entity.PHuman;

public class Human implements PersistanceObject<PHuman>{

	private int id;
	
	private String deviceMac;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		
	}
	@Override
	public PHuman toEntity() {
		PHuman entity = new PHuman();
		entity.setId(this.getId());
		return entity;
	}

	@Override
	public void setModified() {
		Globals.getPersistService().persist(this);
		
	}
}
