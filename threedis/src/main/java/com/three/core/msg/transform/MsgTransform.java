package com.three.core.msg.transform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.three.core.msg.inter.IMessage;

/**
 * 消息转换
 * @author JavaServer
 *
 */
public class MsgTransform {

	
	
	/**
	 * 消息转成字符串
	 * @param msg
	 * @return
	 */
	public static String toJSONString(IMessage msg){
		return JSON.toJSONString(msg);
	}
	
	
	public static IMessage fromJSONString(String jsonMsg,Class<?> clazz){
		Method[] methods = clazz.getMethods();
		Field[] fields = clazz.getFields();
		JSONObject jb = JSONObject.parseObject(jsonMsg);
		clazz.getTypeName();
		if(methods != null && methods.length > 0){
			for(int i=0;i<methods.length;i++){
				Method m = methods[i];
				if(m.getName().startsWith("set")){
					String methodName = getMethodStr(m.getName());
					String typeName = getFieldType(m.getName(),fields);
					try {
						if(!StringUtils.isEmpty(typeName)){
							if(typeName.equals("String")){
								m.invoke(clazz,jb.getString(methodName) );
							}else if(typeName.equals("Long")){
								m.invoke(clazz,jb.getLong(methodName) );
							}else if(typeName.equals("Integer")){
								m.invoke(clazz,jb.getInteger(methodName) );
							}else if(typeName.equals("Short")){
								m.invoke(clazz,jb.getShort(methodName) );
							}
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		try {
			return (IMessage)clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	private static String getMethodStr(String methodName) {
		methodName = methodName.replaceFirst("set", "");
		String first = methodName.substring(0,1);
		methodName = methodName.replaceFirst(first, first.toLowerCase());
		return methodName;
	}
	private static String getFieldType(String methodName, Field[] fields) {
		for(int i=0;i<fields.length;i++){
			Field field = fields[i];
			String filedName = field.getName();
			if(methodName.equals(filedName)){
				return field.getType().getSimpleName();
			}
		}
		return null;
	}
}
