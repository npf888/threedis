package com.three.database.dbutils.name;

import java.util.ArrayList;
import java.util.List;

public class NameUtils {

	public static String getUnderlineName(String entityName) {

		List<Integer> upperIndexList = new ArrayList<Integer>();
		for(int i=0;i<entityName.length();i++){
			if(i == 0){//首字母不�?
				continue;
			}
			char ch = entityName.charAt(i);
			if(Character.isUpperCase(ch)){
				upperIndexList.add(i);
			}
		}
		StringBuilder  sb = new StringBuilder (entityName.toLowerCase()); 
		for(int i=0;i<upperIndexList.size();i++){
			int index = upperIndexList.get(i);
			sb.insert(i+index, "_");
		}
		return sb.toString();
	}

}
