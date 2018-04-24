package com.three.player.manager;

import com.three.globals.ManagerOfHuman;
import com.three.player.db.Human;

public class HumanManager implements ManagerOfHuman{

	private Human owner;
	
	public HumanManager(Human human) {
		this.owner=human;
	}

	@Override
	public void load() {
		
		
	}
	
	

}
