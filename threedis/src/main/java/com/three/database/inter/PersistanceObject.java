package com.three.database.inter;

import com.three.player.db.Human;


public interface PersistanceObject<T extends BaseEntity> {

	public Long getId();
	public void fromEntity(T entity);
	public T toEntity();
	
	/**
	 * 所有的 修改 都面向 redis 然后再由 redis去 更新数据库
	 */
	public void setModified(Human human);
}
