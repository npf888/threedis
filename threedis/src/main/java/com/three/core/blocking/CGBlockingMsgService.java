package com.three.core.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.three.core.msg.inter.IMessage;
import com.three.globals.InitService;
import com.three.player.msg.CGLoginIn;


/**
 * 用户请求消息的 处理 service
 * @author JavaServer
 *
 */
public class CGBlockingMsgService implements InitService{

	Logger logger = Logger.getLogger(CGBlockingMsgService.class);
	//netty 接收到用户的消息后 第一步先放到这里
	private List<IMessage> messageCache = new ArrayList<IMessage>();
	
	
	
	//专门 用于 把  messageCache 中的 消息 放到 cgQueue 中 （阻塞的） 
	private Thread thread = null;
	
	//接收用户请求的消息队列
	private BlockingQueue<IMessage> cgQueue = new LinkedBlockingQueue<IMessage>();
	
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
			cgQueue.put(msg);
		} catch (InterruptedException e) {
			logger.error("用户消息 放入阻塞队列时 错误：",e);
		}
	}
	
	/**
	 * 消费消息
	 * 
	 * @param msg
	 */
	private void process(){
		IMessage msg = cgQueue.poll();
		if(msg != null){
			msg.execute();
		}
	}
	
}
