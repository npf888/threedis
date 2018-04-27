package com.three.gift.db;

import com.three.database.inter.PersistanceObject;
import com.three.gift.db.entity.PGift;
import com.three.globals.Globals;
import com.three.player.db.Human;

/**
 * 存在于内存中
 * @author JavaServer
 *
 */
public class Gift implements PersistanceObject<PGift>{

	
	private Long id;
	private Long humanId;
	private String name;
	
	@Override
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

	public Long getHumanId() {
		return humanId;
	}
	public void setHumanId(Long humanId) {
		this.humanId = humanId;
	}

	
	
	
	@Override
	public void fromEntity(PGift entity){
		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setHumanId(entity.getHumanId());
	}
	
	@Override
	public PGift toEntity(){
		PGift giftEntity = new PGift();
		giftEntity.setId(this.getId());
		giftEntity.setName(this.getName());
		giftEntity.setHumanId(this.getHumanId());
		return giftEntity;
	}
	@Override
	public void setModified(Human human) {
		Globals.getRedisService().updateRedis(human, this);
//		Globals.getPersistService().persist(this);
		
	}
	
}
