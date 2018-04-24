package com.three.gift.db;

import com.three.database.inter.PersistanceObject;
import com.three.gift.db.entity.GiftEntity;

/**
 * 存在于内存中
 * @author JavaServer
 *
 */
public class Gift implements PersistanceObject<GiftEntity>{

	
	private int id;
	
	
	@Override
	public void fromEntity(GiftEntity entity){
		this.setId(entity.getId());
	}
	
	@Override
	public GiftEntity toEntity(){
		GiftEntity giftEntity = new GiftEntity();
		giftEntity.setId(this.getId());
		return giftEntity;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
