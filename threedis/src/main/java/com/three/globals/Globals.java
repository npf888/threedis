package com.three.globals;


/**
 * 
 * 全局变量
 * @author JavaServer
 *
 */
public class Globals {

	private static MsgProvider msgProvider = new MsgProvider();
	
	public static MsgProvider getMsgProvider(){
		return msgProvider;
	}
}
