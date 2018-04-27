package com.three.database.inter;



public interface DBService<T extends BaseEntity> {

	void update(BaseEntity base);

	T findById(int id);
	
	int getNum();
	
	Long create(BaseEntity entity);
}
