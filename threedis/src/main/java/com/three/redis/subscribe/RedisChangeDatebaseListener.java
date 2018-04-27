package com.three.redis.subscribe;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.three.redis.RedisCacheManager;
import com.three.redis.subscribe.changedatabasedispatcher.RedisChangeDatabaseDispatcher;
/**
 * 把 redis中的数据更新的数据库
 * @author JavaServer
 *
 */
@Component
public class RedisChangeDatebaseListener implements MessageListener{

	Logger logger = Logger.getLogger(RedisChangeDatebaseListener.class);
	
	@Autowired
	private RedisChangeDatabaseDispatcher redisChangeDatabaseDispatcher;
	
	@Autowired
	private RedisCacheManager redisCacheManager;
	



	@Override
	public void onMessage(Message message, byte[] pattern) {
		byte[] body = message.getBody();//请使用valueSerializer  
        byte[] channel = message.getChannel();
        //请参考配置文件，本例中key，value的序列化方式均为string。  
        //其中key必须为stringSerializer。和redisTemplate.convertAndSend对应  
        String bodyMsg = (String)redisCacheManager.getRedisTemplate().getValueSerializer().deserialize(body);  
        String topicChannel = (String)redisCacheManager.getRedisTemplate().getStringSerializer().deserialize(channel);  
		logger.info( "topicChannel:" + topicChannel+" ---- " +bodyMsg);
		
		if(StringUtils.isBlank(bodyMsg)){
			logger.info( "当前通道：topicChannel:" + topicChannel+"  收到的message为空 ");
			return;
		}
		
		String[] msgs = bodyMsg.split(":");
		String ClassName = msgs[0];
		String id = msgs[1];
		//分发任务
		redisChangeDatabaseDispatcher.dispatcher(ClassName,id);
		
	}

}
