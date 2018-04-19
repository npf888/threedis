package com.three.core.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.three.core.msg.inter.IMessage;
import com.three.globals.InitService;

/**
 * ���� ���û���Ϣ�� service  
 * @author JavaServer
 *
 */
public class GCBlockingMsgService implements InitService{
	Logger logger = Logger.getLogger(GCBlockingMsgService.class);
	// �û���Ϣִ���� ֮�� ���ظ��û�����Ϣ �ŵ��������
	private List<IMessage> messageCache = new ArrayList<IMessage>();
	
	
	
	//ר�� ���� ��  messageCache �е� ��Ϣ �ŵ� cgQueue �� �������ģ� 
	private Thread thread = null;
	
	//�����û��������Ϣ����
	private BlockingQueue<IMessage> gcQueue = new LinkedBlockingQueue<IMessage>();
	
	/**
	 * ��ʼ�� �߳�
	 */
	public void init(){
		Runnable run = new Runnable() {
			@Override
			public void run() {
				while(true){
					if(messageCache.size()>0){// ������Ϣ
						put(messageCache.remove(0));
					}else{//������Ϣ
						process();
					}
				}
			}
		};
		thread = new Thread(run);
		thread.start();
	}
	
	
	
	/**
	 * �յ����û�����Ϣ�ŵ� ������
	 */
	public void putMsgIntoCache(IMessage msg){
		messageCache.add(msg);
	}
	
	/**
	 * ����Ϣ
	 * @param msg
	 */
	private  void put(IMessage msg){
		try {
			gcQueue.put(msg);
		} catch (InterruptedException e) {
			logger.error("�û���Ϣ ������������ʱ ����",e);
		}
	}
	
	/**
	 * ������Ϣ
	 * @param msg
	 */
	private void process(){
		IMessage msg = gcQueue.poll();
		if(msg != null){//�����Ϣֱ�ӷ��ظ��û�
			
		}
		
	}
}
