package com.three.gift.msg;

import com.three.core.msg.inter.IMessage;
import com.three.core.msg.mcode.percode.GiftCode;
import com.three.core.msg.obj.BaseMessageJSON;
import com.three.gift.handler.GiftHandlerFactory;
import com.three.player.playerObj.Player;

/**
 * 
 * 发送礼物
 * @author JavaServer
 *
 */
public class GCSendGift extends BaseMessageJSON{


	private String name="小明";
	private int age=18;
	private long height=180l;
	
	
	public GCSendGift(){
		super(GiftCode.GC_SEND_GIFT,IMessage.GC_MSG_TYPE);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public long getHeight() {
		return height;
	}


	public void setHeight(long height) {
		this.height = height;
	}


	
}
