package com.three.core.msg.mtype;


/**
 * ��Ϣ�����б��
 * @author JavaServer
 *
 */
public class NetMessageType {

	
	// ////////////

		private static int BASE_NUMBER = 1000;
		public static final int NUMBER_PER_SYS = 1000;

		// ��ģ��ͨ�õ���Ϣ 1000 - 2000 
		public static int COMMON_BEGIN = BASE_NUMBER;

		// ��ҵ�¼�˳�ģ�� 2000 - 3000
		public static int PLAYER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
		
		// ����ģ��
		public static int GIFT_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);

}
