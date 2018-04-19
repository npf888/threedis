package com.three.core.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.three.core.msg.inter.IMessage;
import com.three.globals.InitService;

/**
 * 发送 给用户消息的 service  
 * @author JavaServer
 *
 */
public class GCBlockingMsgService implements InitService{
	Logger logger = Logger.getLogger(GCBlockingMsgService.class);
	// 用户消息执行完 之后 返回给用户的消息 放到这个缓存
	private List<IMessage> messageCache = new ArrayList<IMessage>();
	
	
	
	//专门 用于 把  messageCache 中的 消息 放到 cgQueue 中 （阻塞的） 
	private Thread thread = null;
	
	//接收用户请求的消息队列
	private BlockingQueue<IMessage> gcQueue = new LinkedBlockingQueue<IMessage>();
	
	/**
	 * 初始化 线程
	 */
	public void init(){
		Runnable run = new Runnable() {
			@Override
			public void run() {
				while(true){
					if(messageCache.size()>0){// 放置消息
						put(messageCache.remove(0));
					}else{//消费消息
						process();
					}
				}
			}
		};
		thread = new Thread(run);
		thread.start();
	}
	
	
	
	/**
	 * 收到的用户的消息放到 缓存中
	 */
	public void putMsgIntoCache(IMessage msg){
		messageCache.add(msg);
	}
	
	/**
	 * 放消息
	 * @param msg
	 */
	private  void put(IMessage msg){
		try {
			gcQueue.put(msg);
		} catch (InterruptedException e) {
			logger.error("用户消息 放入阻塞队列时 错误：",e);
		}
	}
	
	/**
	 * 消费消息
	 * @param msg
	 */
	private void process(){
		IMessage msg = gcQueue.poll();
		if(msg != null){//这个消息直接返回给用户
			
		}
		
	}
}
