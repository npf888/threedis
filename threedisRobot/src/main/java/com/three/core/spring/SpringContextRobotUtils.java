package com.three.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring  ����
 * @author JavaServer
 *
 */
public class SpringContextRobotUtils {

	
	private  ApplicationContext ac = null;
	
	public SpringContextRobotUtils(){
		ac = new ClassPathXmlApplicationContext("classpath:spring/robotApplication.xml");
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Object getBean(Class<?> clazz){
		return ac.getBean(clazz);
	}
	
	
	
}
