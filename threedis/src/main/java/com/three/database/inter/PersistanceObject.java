package com.three.database.inter;


public interface PersistanceObject<T extends BaseEntity> {

	public void fromEntity(T entity);
	public T toEntity();
	
	
	public void setModified();
}
