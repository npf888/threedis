package com.three.gift.handler;

import com.three.core.handler.MessagerHandler;
import com.three.core.msg.inter.IMessage;
import com.three.gift.msg.CGSendGift;
import com.three.player.playerObj.Player;

public class GiftMessagerHandler implements MessagerHandler{



	/**
	 * ÿ��handler�� ͳһ���
	 */
	@Override
	public void execute(Player player, IMessage curMessage) {
		boolean open = true;//�������ļ��� ����
		if(!open){//���� ��� ����ģ�鲻�õĻ� �ͻ�ر�
			return;
		}
		
		if(curMessage instanceof CGSendGift){
			sendGift();
		}
		
	};



	private void sendGift(){
		
	};
}