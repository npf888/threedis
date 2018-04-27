package com.three.redis.subscribe;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.three.redis.RedisCacheManager;
import com.three.redis.subscribe.testdispatcher.RedisTestDispatcher;
@Component
public class RedisTestistener implements MessageListener{

	Logger logger = Logger.getLogger(RedisTestistener.class);
	
	
	@Autowired
	private RedisTestDispatcher redisTestDispatcher;
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	



	@Override
	public void onMessage(Message message, byte[] pattern) {
		byte[] body = message.getBody();//请使用valueSerializer  
        byte[] channel = message.getChannel();  
        //请参考配置文件，本例中key，value的序列化方式均为string。  
        //其中key必须为stringSerializer。和redisTemplate.convertAndSend对应  
        String itemValue = (String)redisCacheManager.getRedisTemplate().getValueSerializer().deserialize(body);  
        String topic = (String)redisCacheManager.getRedisTemplate().getStringSerializer().deserialize(channel);  
		logger.info( "Message body:" + itemValue );
		logger.info( "Message channel:" + topic );
		
		
		
	}

}
