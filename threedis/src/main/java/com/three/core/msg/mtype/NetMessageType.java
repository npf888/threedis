package com.three.core.msg.mtype;


/**
 * ��Ϣ�����б��
 * @author JavaServer
 *
 */
public class NetMessageType {

	
	// ////////////

		private static short BASE_NUMBER = 1000;
		public static final short NUMBER_PER_SYS = 1000;

		// ��ģ��ͨ�õ���Ϣ 1000 - 2000 
		public static short COMMON_BEGIN = BASE_NUMBER;

		// ��ҵ�¼�˳�ģ�� 2000 - 3000
		public static short PLAYER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
		
		// ����ģ��
		public static short GIFT_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);

}
