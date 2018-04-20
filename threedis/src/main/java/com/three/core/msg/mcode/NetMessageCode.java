package com.three.core.msg.mcode;


/**
 * 消息的所有编号
 * @author JavaServer
 *
 */
public class NetMessageCode {

	
	// ////////////

		private static int BASE_NUMBER = 1000;
		public static final int NUMBER_PER_SYS = 1000;

		// 各模块通用的消息 1000 - 2000 
		public static int COMMON_BEGIN = BASE_NUMBER;

		// 玩家登录退出模块 2000 - 3000
		public static int PLAYER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
		
		// 礼物模块
		public static int GIFT_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);

}
