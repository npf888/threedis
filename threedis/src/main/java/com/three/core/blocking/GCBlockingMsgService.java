package com.three.core.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.three.core.msg.inter.IMessage;
import com.three.core.session.NettyClientSession;
import com.three.globals.InitService;

/**
 * 处理返回给 客户端的  service  
 * @author JavaServer
 *
 */
@Service
public class GCBlockingMsgService implements InitService{
	Logger logger = Logger.getLogger(GCBlockingMsgService.class);
	
	
	//缓存
	private List<IMessage> messageCache = new ArrayList<IMessage>();
	
	
	
	//处理线程
	private Thread thread = null;
	
	// 返回给客户端的消息队列
	private BlockingQueue<IMessage> gcQueue = new LinkedBlockingQueue<IMessage>();
	
	/**
	 * 初始化
	 */
	public void init(){
		Runnable run = new Runnable() {
			@Override
			public void run() {
				while(true){
					if(messageCache.size()>0){// 把缓存 放入队列
						put(messageCache.remove(0));
					}else{//返回消息
						process();
					}
				}
			}
		};
		thread = new Thread(run);
		thread.start();
	}
	
	
	
	/**
	 * 放入缓存
	 * @param nettyClientSession 
	 */
	public void putMsgIntoCache(IMessage msg){
		messageCache.add(msg);
	}
	
	/**
	 * 放入队列
	 * @param msg
	 */
	private  void put(IMessage msg){
		try {
			gcQueue.put(msg);
		} catch (InterruptedException e) {
			logger.error("返回消息放入队列异常：",e);
		}
	}
	
	/**
	 * 处理消息
	 * @param msg
	 */
	private void process(){
		IMessage msg = gcQueue.poll();
		if(msg != null){
			msg.getNettyClientSession().sendMessageToCtx(msg);
		}
		
	}
}
