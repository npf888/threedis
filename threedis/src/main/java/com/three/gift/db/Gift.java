package com.three.gift.db;

import com.three.database.inter.PersistanceObject;
import com.three.gift.db.entity.PGift;
import com.three.globals.Globals;

/**
 * 存在于内存中
 * @author JavaServer
 *
 */
public class Gift implements PersistanceObject<PGift>{

	
	private Long id;
	private Integer passportId;
	private String name;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}

	
	
	
	@Override
	public void fromEntity(PGift entity){
		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setPassportId(entity.getPassportId());
	}
	
	@Override
	public PGift toEntity(){
		PGift giftEntity = new PGift();
		giftEntity.setId(this.getId());
		giftEntity.setName(this.getName());
		giftEntity.setPassportId(this.getPassportId());
		return giftEntity;
	}
	@Override
	public void setModified() {
		
		Globals.getPersistService().persist(this);
		
	}
	
}
