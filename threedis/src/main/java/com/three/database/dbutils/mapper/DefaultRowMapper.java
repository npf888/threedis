package com.three.database.dbutils.mapper;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.three.database.dbutils.name.NameHandler;
import com.three.database.dbutils.util.ClassUtils;

public class DefaultRowMapper implements RowMapper<Object> {
    /** 转换的目标对�? */
    private Class<?>    clazz;
    /** 名称处理�? */
    private NameHandler nameHandler;
    public DefaultRowMapper(Class<?> clazz, NameHandler nameHandler) {
        this.clazz = clazz;
        this.nameHandler = nameHandler;
    }
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Object entity = ClassUtils.newInstance(this.clazz);
        BeanInfo beanInfo = ClassUtils.getSelfBeanInfo(this.clazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String column = nameHandler.getColumnName(pd.getName());
            Method writeMethod = pd.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
            }
            try {
                writeMethod.invoke(entity, resultSet.getObject(column));
            } catch (Exception e) {
            	e.printStackTrace();
//                throw new MincoderException(e);
            	return null;
            }
        }
        return entity;
    }
}
