package com.three.core.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.three.core.msg.inter.IMessage;
import com.three.globals.InitService;


/**
 * 处理接受消息的   service
 * @author JavaServer
 *
 */
@Service
public class CGBlockingMsgService implements InitService{

	Logger logger = Logger.getLogger(CGBlockingMsgService.class);
	//netty 缓存数据 （中间的一层）
	private List<IMessage> messageCache = new ArrayList<IMessage>();
	
	
	
	//执行线程
	private Thread thread = null;
	
	// 接收到消息的队列
	private BlockingQueue<IMessage> cgQueue = new LinkedBlockingQueue<IMessage>();
	
	/**
	 * 初始化线程 并开启
	 */
	public void init(){
		Runnable run = new Runnable() {
			@Override
			public void run() {
				while(true){
					if(messageCache.size()>0){// 把 缓存放入队列
						put(messageCache.remove(0));
					}else{//处理消息
						process();
					}
				}
			}
		};
		thread = new Thread(run);
		thread.start();
	}
	
	
	
	/**
	 * 把消息 先放入缓存
	 */
	public void putMsgIntoCache(IMessage msg){
		messageCache.add(msg);
	}
	
	/**
	 * 再把消息放入队列
	 * @param msg
	 */
	private  void put(IMessage msg){
		try {
			cgQueue.put(msg);
		} catch (InterruptedException e) {
			logger.error("处理 接收消息异常：",e);
		}
	}
	
	/**
	 * 处理消息
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
