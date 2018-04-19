package com.three.core.msg.mtype;


/**
 * 消息的所有编号
 * @author JavaServer
 *
 */
public class NetMessageType {

	
	// ////////////

		private static short BASE_NUMBER = 1000;
		public static final short NUMBER_PER_SYS = 1000;

		// 各模块通用的消息 1000 - 2000 
		public static short COMMON_BEGIN = BASE_NUMBER;

		// 玩家登录退出模块 2000 - 3000
		public static short PLAYER_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);
		
		// 礼物模块
		public static short GIFT_BEGIN = (BASE_NUMBER += NUMBER_PER_SYS);

}
