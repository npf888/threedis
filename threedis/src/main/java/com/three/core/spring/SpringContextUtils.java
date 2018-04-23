package com.three.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.three.core.msg.msgMap.MsgProvider;

/**
 * spring  ºËÐÄ
 * @author JavaServer
 *
 */
public class SpringContextUtils {

	
	private  ApplicationContext ac = null;
	
	public SpringContextUtils(){
		ac = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Object getBean(Class<?> clazz){
		return ac.getBean(clazz);
	}
	
	
	
}
