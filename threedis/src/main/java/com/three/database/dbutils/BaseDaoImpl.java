package com.three.database.dbutils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.three.database.dbutils.mapper.DefaultRowMapper;
import com.three.database.dbutils.name.DefaultNameHandler;
import com.three.database.dbutils.name.NameHandler;
import com.three.database.dbutils.page.Pager;
import com.three.database.dbutils.page.PagingOrder;
import com.three.database.dbutils.util.SqlContext;
import com.three.database.dbutils.util.SqlUtils;


public class BaseDaoImpl<T> implements BaseDao<T> { 
	
	Logger logger = Logger.getLogger(BaseDaoImpl.class);
    /** 具体操作的实体类对象 */
    private Class<T>       entityClass;
 
    /** 名称加工处理*/
    private NameHandler    nameHandler;
 
    /** spring jdbcTemplate 对象 */
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    
    
    /**
     * 构造方法，获取运行时的具体实体对象
     */
    public BaseDaoImpl() {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) superclass;
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }
    
    
    
    
    
    /**
     * 获取实际运行时的名称处理
     *
     * @return
     */
    private NameHandler getActualNameHandler() {
        if (nameHandler == null) {
            synchronized (this) {
                if (nameHandler == null) {
                    nameHandler = this.getNameHandler();
                }
            }
        }
        return nameHandler;
    }
 
    /**
     * 得到名称处理器，子类覆盖此方法实现自己的名称转换处理
     *
     * @return
     */
    protected NameHandler getNameHandler() {
        return new DefaultNameHandler();
    }
 
    /**
     *插入
     *
     * @param entity
     */
    @Override
    public Long insert(T entity) {
        final SqlContext sqlContext = SqlUtils.buildInsertSql(entity, this.getActualNameHandler());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sqlContext.getSql().toString(),
                    new String[] { sqlContext.getPrimaryKey() });
                int index = 0;
                for (Object param : sqlContext.getParams()) {
                    index++;
                    ps.setObject(index, param);
                }
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
 
    /**
     * 更新记录
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        SqlContext sqlContext = SqlUtils.buildUpdateSql(entity, this.getActualNameHandler());
        jdbcTemplate.update(sqlContext.getSql().toString(), sqlContext.getParams().toArray());
    }
 
    /**
     * 删除记录
     *
     * @param id
     */
    @Override
    public void delete(Serializable id) {
        String tableName = this.getActualNameHandler().getTableName(entityClass.getSimpleName());
        String primaryName = this.getNameHandler().getPrimaryName(entityClass.getSimpleName());
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryName + " = ?";
        jdbcTemplate.update(sql, id);
    }
 
    /**
     * 删除�?有记�?
     */
    @Override
    public void deleteAll() {
        String tableName = this.getActualNameHandler().getTableName(entityClass.getSimpleName());
        String sql = " TRUNCATE TABLE " + tableName;
        jdbcTemplate.execute(sql);
    }
 
    /**
     * 得到记录
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public T getById(Serializable id) {
        String tableName = this.getNameHandler().getTableName(entityClass.getSimpleName());
        String primaryName = this.getNameHandler().getPrimaryName(entityClass.getSimpleName());
        String sql = "SELECT * FROM " + tableName + " WHERE " + primaryName + " = ?";
        try{
        	return (T) jdbcTemplate.query(sql,new DefaultRowMapper(entityClass, this.getActualNameHandler()), id).get(0);
        }catch(Exception e){
        	return null;
        }
    }
 
    /**
     * 查询�?有记�?
     *
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<T> findAll() {
        String sql = "SELECT * FROM "
                     + this.getActualNameHandler().getTableName(entityClass.getSimpleName());
        return (List<T>) jdbcTemplate.query(sql,
            new DefaultRowMapper(entityClass, this.getActualNameHandler()));
    }
 
    /**
     * 查询记录�?
     *
     * @param entity
     * @return
     */
    public int queryCount(T entity) {
        String tableName = this.getActualNameHandler().getTableName(entityClass.getSimpleName());
        StringBuilder countSql = new StringBuilder("select count(*) from ");
        countSql.append(tableName);
        SqlContext sqlContext = SqlUtils.buildQueryCondition(entity, this.getActualNameHandler());
        if (sqlContext.getSql().length() > 0) {
            countSql.append(" where ");
            countSql.append(sqlContext.getSql());
        }
        
        List<Map<String,Object>> lists = jdbcTemplate.queryForList(countSql.toString(), sqlContext.getParams().toArray());
    	int totalCount = 0;
    	if(lists != null && lists.size()>0){
    		totalCount = lists.size();
    	}
    	return totalCount;
       
    }
    /**
     * 查询记录
     *
     * @param entity
     * @return
     */
    public List<?> queryList(T entity) {
    	String tableName = this.getActualNameHandler().getTableName(entityClass.getSimpleName());
    	StringBuilder countSql = new StringBuilder("select * from ");
    	countSql.append(tableName);
    	SqlContext sqlContext = SqlUtils.buildQueryCondition(entity, this.getActualNameHandler());
    	if (sqlContext.getSql().length() > 0) {
    		countSql.append(" where ");
    		countSql.append(sqlContext.getSql());
    	}
    	List<Object> paramList = sqlContext.getParams();
    	Object[] params = new Object[paramList.size()];
    	String paramStr = "";
    	for(int i=0;i< paramList.size();i++){
    		Object obj = paramList.get(i);
    		params[i] = obj;
    		paramStr+=obj+",";
    	}
    	logger.info("queryList::sql = "+countSql.toString()+"; params:"+paramStr);
        List<?> objList = jdbcTemplate.query(countSql.toString(),params, new DefaultRowMapper(entityClass, this.getActualNameHandler()));
        return objList;
    }
 
    /**
     * 查询分页列表
     *
     * @param entity
     * @param searchAAdminVO 
     * @return
     */
    @SuppressWarnings("unchecked")
    public Pager queryPageList(T entity,PagingOrder pagingOrder) {
        Pager pager = new Pager();
        pager.setBeginIndex((pagingOrder.getCurPage()-1)*pagingOrder.getItemsPerPage());
        pager.setCurPage(pagingOrder.getCurPage());
        pager.setItemsPerPage(pagingOrder.getItemsPerPage());
 
        String tableName = this.getActualNameHandler().getTableName(entityClass.getSimpleName());
        String primaryName = this.getActualNameHandler()
            .getPrimaryName(entityClass.getSimpleName());
        StringBuilder querySql = new StringBuilder("select * from ");
        StringBuilder countSql = new StringBuilder("select count(*) from ");
        querySql.append(tableName);
        countSql.append(tableName);
        //不调用queryCount方法，条件共同组装一次，减少反射获取的次�?
        SqlContext sqlContext = SqlUtils.buildQueryConditionLike(entity, this.getActualNameHandler());
        if (sqlContext.getSql().length() > 0) {
            querySql.append(" where ");
            countSql.append(" where ");
            querySql.append(sqlContext.getSql());
            countSql.append(sqlContext.getSql());
        }
        querySql.append(" order by ");
        querySql.append(primaryName);
        querySql.append(" desc ");
        querySql.append("limit ?,?");
        List<Object> queryParams = new ArrayList<Object>();
        List<Object> queryParam2s = new ArrayList<Object>();
        for(Object  obj:sqlContext.getParams()){
        	String strParam = String.valueOf(obj);
        	queryParams.add("%"+strParam+"%");
        	queryParam2s.add("%"+strParam+"%");
        }
        queryParams.add(pager.getBeginIndex());
        queryParams.add(pager.getItemsPerPage());
 
        @SuppressWarnings("unchecked")
		List<T> list = (List<T>) jdbcTemplate.query(querySql.toString(), queryParams.toArray(),
            new DefaultRowMapper(entityClass, this.getActualNameHandler()));
 
        List<Map<String,Object>> lists = jdbcTemplate.queryForList(countSql.toString(), queryParam2s.toArray());
        Long totalCount = 0l;
        if(lists != null && lists.size()>0){
        	totalCount = (Long) lists.get(0).get("count(*)");
        }
        pager.setList(list);
        pager.setItems(totalCount.intValue());
        return pager;
    }
    
    
    
    /**
     * 得到记录
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	@Override
    public T execute(String sql,Object[] objParam,Class<T> clazz) {
        try{
        	return (T) jdbcTemplate.query(sql,objParam,new DefaultRowMapper(clazz, this.getActualNameHandler()));
        }catch(Exception e){
        	return null;
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<?> executeSql(String sql,Object[] objParam,Class<?> clazz) {
    	try{
    		return (List<?>) jdbcTemplate.query(sql,objParam,new DefaultRowMapper(clazz, this.getActualNameHandler()));
    	}catch(Exception e){
    		return null;
    	}
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<?> executeSql(String sql,Object[] objParam) {
    	try{
    		return (List<?>) jdbcTemplate.queryForList(sql,objParam);
    	}catch(Exception e){
    		return null;
    	}
    }
}
