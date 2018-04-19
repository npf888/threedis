package com.three.gift.handler;

import com.three.core.handler.MessagerHandler;


/**
 * 聊天消息处理器提供类
 * @author Thinker
 */
public class GiftHandlerFactory
{
	private static final MessagerHandler handler = new GiftMessagerHandler();
	
	public static MessagerHandler getHandler() 
	{
		return handler;
	}
	
}
