package com.three.globals;


/**
 * 
 * ȫ�ֱ���
 * @author JavaServer
 *
 */
public class Globals {

	private static MsgProvider msgProvider = new MsgProvider();
	
	public static MsgProvider getMsgProvider(){
		return msgProvider;
	}
}
