package com.three.database;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.three.globals.InitService;
/**
 * 生成 主键 和 passportId
 * @author JavaServer
 *
 */
@Service
public class IDService implements InitService{

	
	
	@Override
	public void init() {
		
		
	}

	//生成主键ID
	public Long getPriID(PerType type){
		Long now = new Date().getTime();
		Random random  = new Random();
		int first = random.nextInt(100000);
		int second = random.nextInt(100000);
		int three = random.nextInt(100000);
		long id = now.longValue()+first+second+three;
		String idStr = type.getIndex()+(id+"");
		id = Long.valueOf(idStr);
		return id;
	}
	
	public String getCharId(Class<?> clazz,int num){
		return clazz.getSimpleName()+":"+num;
	}
	
	/**
	 * 每种业务一个类型
	 * @author JavaServer
	 *
	 */
	public enum PerType{
		HUMAN("用户", 1), 
		GIFT("礼物", 2);  
	    // 成员变量  
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private PerType(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    // 普通方法  
	    public static String getName(int index) {  
	        for (PerType c : PerType.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    } 
	}
}
