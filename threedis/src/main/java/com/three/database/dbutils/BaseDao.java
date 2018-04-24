package com.three.database.dbutils;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T> {

	void delete(Serializable id);

	void deleteAll();

	T getById(Serializable id);

	Long insert(T entity);

	void update(T entity);

	List<T> findAll();



	T execute(String sql, Object[] objParam, Class<T> clazz);

	List<?> executeSql(String sql, Object[] objParam, Class<?> clazz);

	List<?> executeSql(String sql, Object[] objParam);
	
}
