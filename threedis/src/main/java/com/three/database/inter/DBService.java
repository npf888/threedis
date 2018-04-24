package com.three.database.inter;


public interface DBService<T extends BaseEntity> {

	void saveOrUpdate(PersistanceObject<T> base);

	T findById(int id);
	T findByDeviceMac(String deviceMac);
}
