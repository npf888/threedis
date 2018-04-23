package com.three.core.msg.process;

import com.three.core.msg.inter.IMessage;
import com.three.globals.RobotGlobals;
import com.three.player.msg.CGLoginIn;

/**
 * ����һЩ������Ϣ
 * @author JavaServer
 *
 */
public  abstract class RobotBaseMessageHandler  implements RobotMessageHandler{

	
	/**
	 * �� �� ������ ��Ϣ  �����¼��Ϣ
	 */
	@Override
	public void handler(IMessage msg) {
		if(msg instanceof CGLoginIn){
			
			//}else if(msg instanceof GCLoginIn){//�������Ҫ��
				
				
				//ִ�о����ҵ��  ���� ���� new CGSendGift()
		}
			
		handlerSub(msg);
			
		//����Ϣ�ŵ�session ��
		RobotGlobals.getRobotSession().put(msg);
		
	}
	
	
	
	public abstract void handlerSub(IMessage msg);
		
	
}
