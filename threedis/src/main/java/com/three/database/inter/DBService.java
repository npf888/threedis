package com.three.database.inter;



public interface DBService<T extends BaseEntity> {

	void saveOrUpdate(PersistanceObject<T> base);

	T findById(int id);
	
	int getNum();
	
	void create(BaseEntity entity);
}
