package com.three.core.msg.process;

import com.three.core.msg.inter.IMessage;
import com.three.globals.RobotGlobals;
import com.three.player.msg.CGLoginIn;

public class RobotMessageHandler {

	
	//������Ϣ
	public void handler(IMessage msg){
		if(msg instanceof CGLoginIn){
			
		//}else if(msg instanceof GCLoginIn){//�������Ҫ��
			
			
			//ִ�о����ҵ��  ���� ���� new CGSendGift()
		}
		
		
		
		//����Ϣ�ŵ�session ��
		RobotGlobals.getRobotSession().put(msg);
	}
}
