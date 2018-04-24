package com.three.player.manager;

import com.three.globals.ManagerOfHuman;
import com.three.player.db.Human;

public class HumanManager implements ManagerOfHuman{

	private Human human;
	
	public HumanManager(Human human) {
		this.human=human;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}
	
	

}
