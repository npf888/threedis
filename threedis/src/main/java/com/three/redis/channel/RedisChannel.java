package com.three.redis.channel;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 设置 监听通道
 * @author JavaServer
 *
 */
@Component
public class RedisChannel {
	Logger logger = Logger.getLogger(RedisChannel.class);
	
	public static final String change_datebase= "change:datebase";
	public static final String change_test= "change:test";
	
	
	
}
