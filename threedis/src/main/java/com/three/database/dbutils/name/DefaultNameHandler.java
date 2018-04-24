package com.three.database.dbutils.name;


/**
 * 默认名称处理handler
 * 
 * User: liyd
 * Date: 2/12/14
 * Time: 4:51 PM
 */
public class DefaultNameHandler implements NameHandler {
    /** 字段前缀 */
    private static final String PREFIX     = "_";
    /** 主键后缀 */
    private static final String PRI_SUFFIX = "_id";
    /**
     * 根据实体名获取表�?
     *
     * @param entityName
     * @return
     */
    @Override
    public String getTableName(String entityName) {
        //Java属�?�的骆驼命名法转换回数据库下划线“_”分隔的格式
        return NameUtils.getUnderlineName(entityName);
    }
    /**
     * 根据表名获取主键�?
     * 主键�? 就是 id   现在 写死�?
     * @param entityName
     * @return
     */
    @Override
    public String getPrimaryName(String entityName) {
        /*String underlineName = NameUtils.getUnderlineName(entityName);
        //正如前面说到的，数据库列名统�?以�?�_”开始，主键以表名加上�?�_id�? 如user表主键即“_user_id�?
        return PREFIX + underlineName + PRI_SUFFIX;*/
    	return "id";
    }
    /**
     * 根据属�?�名获取列名
     *
     * @param fieldName
     * @return
     */
    @Override
    public String getColumnName(String fieldName) {
        String underlineName = NameUtils.getUnderlineName(fieldName);
        //数据库列名统�?以�?�_”开�?
//        return PREFIX + underlineName;
        return underlineName;
    }
    @Override
    public String getNoUnderLineColumnName(String fieldName) {
    	return fieldName;
    }
}
