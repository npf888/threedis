package com.three.core.msg.mcode;


/**
 * 消息的 code 
 * @author JavaServer
 *
 */
public class NetMessageCode {

	
	// ////////////

		private static int BASE_NUMBER = 1000;
		public static final int NUMBER_PER_SYS = 1000;

		// 范围 1000 - 2000 
		public static int COMMON_BEGIN = BASE_NUMBER;

		//范围 2000 - 3000
		public static int PLAYER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
		
		// 范围 3000 - 4000
		public static int GIFT_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);

}
