package com.three.database.dbutils.name;
/**
 * 名称处理接口
 * 
 * User: liyd
 * Date: 2/12/14
 * Time: 4:51 PM
 */
public interface  NameHandler {
    /**
     * 根据实体名获取表�?
     *
     * @param entityName
     * @return
     */
    public String getTableName(String entityName);
    /**
     * 根据表名获取主键�?
     * 
     * @param entityName
     * @return
     */
    public String getPrimaryName(String entityName);
    /**
     * 根据属�?�名获取列名
     *
     * @param fieldName
     * @return
     */
    public String getColumnName(String fieldName);
	String getNoUnderLineColumnName(String fieldName);
}
