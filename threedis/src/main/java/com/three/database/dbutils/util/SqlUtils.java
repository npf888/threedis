package com.three.database.dbutils.util;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.three.database.dbutils.name.NameHandler;


public class SqlUtils {
    /** 日志对象 */
    private static final Logger LOG = Logger.getLogger(SqlUtils.class);
    /**
     * 构建insert语句
     *
     * @param entity 实体映射对象
     * @param nameHandler 名称转换处理�?
     * @return
     */
    public static SqlContext buildInsertSql(Object entity, NameHandler nameHandler) {
        Class<?> clazz = entity.getClass();
        String tableName = nameHandler.getTableName(clazz.getSimpleName());
        String primaryName = nameHandler.getPrimaryName(clazz.getSimpleName());
        StringBuilder sql = new StringBuilder("insert into ");
        List<Object> params = new ArrayList<Object>();
        sql.append(tableName);
        //获取属�?�信�?
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        sql.append("(");
        StringBuilder args = new StringBuilder();
        args.append("(");
        for (PropertyDescriptor pd : pds) {
            Object value = getReadMethodValue(pd.getReadMethod(), entity);
            if (value == null) {
                continue;
            }
            sql.append(nameHandler.getColumnName(pd.getName()));
            args.append("?");
            params.add(value);
            sql.append(",");
            args.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        args.deleteCharAt(args.length() - 1);
        args.append(")");
        sql.append(")");
        sql.append(" values ");
        sql.append(args);
        return new SqlContext(sql, primaryName, params);
    }
    /**
     * 构建更新sql
     * 
     * @param entity
     * @param nameHandler
     * @return
     */
    public static SqlContext buildUpdateSql(Object entity, NameHandler nameHandler) {
        Class<?> clazz = entity.getClass();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        String tableName = nameHandler.getTableName(clazz.getSimpleName());
        String primaryName = nameHandler.getPrimaryName(clazz.getSimpleName());
        //获取属�?�信�?
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        sql.append("update ");
        sql.append(tableName);
        sql.append(" set ");
        Object primaryValue = null;
        for (PropertyDescriptor pd : pds) {
            Object value = getReadMethodValue(pd.getReadMethod(), entity);
            if (value == null) {
                continue;
            }
            String columnName = nameHandler.getColumnName(pd.getName());
            if (primaryName.equalsIgnoreCase(columnName)) {
                primaryValue = value;
            }
            sql.append(columnName);
            sql.append(" = ");
            sql.append("?");
            params.add(value);
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where ");
        sql.append(primaryName);
        sql.append(" = ?");
        params.add(primaryValue);
        return new SqlContext(sql, primaryName, params);
    }
    /**
     * 构建查询条件
     * 
     * @param entity
     * @param nameHandler
     */
    public static SqlContext buildQueryCondition(Object entity, NameHandler nameHandler) {
        //获取属�?�信�?
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(entity.getClass());
        //        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entityClass);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        StringBuilder condition = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        int count = 0;
        for (PropertyDescriptor pd : pds) {
        	
            Object value = getReadMethodValue(pd.getReadMethod(), entity);
            if (value == null) {
                continue;
            }
            if (count > 0) {
                condition.append(" and ");
            }
            condition.append(nameHandler.getColumnName(pd.getName()));
            condition.append(" = ?");
            params.add(value);
            count++;
        }
        return new SqlContext(condition, null, params);
    }
    /**
     * 构建查询条件
     * 
     * @param entity
     * @param nameHandler
     */
    public static SqlContext buildQueryConditionLike(Object entity, NameHandler nameHandler) {
    	//获取属�?�信�?
    	BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(entity.getClass());
    	//        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entityClass);
    	PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    	StringBuilder condition = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	int count = 0;
    	for (PropertyDescriptor pd : pds) {
    		/*if(pd.getName().equals("id")){
        		Integer id = (Integer)pd.getValue("id");
        		if(id == null || id.intValue() == 0){
        			continue;
        		}
        	}*/
    		Object value = getReadMethodValue(pd.getReadMethod(), entity);
    		if (value == null) {
    			continue;
    		}
    		if (count > 0) {
    			condition.append(" or ");
    		}
    		condition.append(nameHandler.getColumnName(pd.getName()));
    		condition.append(" like ?");
    		params.add(value);
    		count++;
    	}
    	return new SqlContext(condition, null, params);
    }
    /**
     * 获取属�?��??
     *
     * @param readMethod
     * @param entity
     * @return
     */
    private static Object getReadMethodValue(Method readMethod, Object entity) {
        if (readMethod == null) {
            return null;
        }
        try {
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                readMethod.setAccessible(true);
            }
            return readMethod.invoke(entity);
        } catch (Exception e) {
            LOG.error("获取属�?��?�失�?", e);
//            throw new MincoderException(e);
            return null;
        }
    }
}
