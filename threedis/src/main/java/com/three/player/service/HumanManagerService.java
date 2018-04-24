package com.three.player.service;

import com.three.player.db.Human;
import com.three.player.manager.HumanManager;

/**
 * 用户的manager 服务
 * @author JavaServer
 *
 */
public class HumanManagerService {

	
	private HumanManager  humanManager;
	//先 初始化  所有的 manager
	public void init(Human human) {
		humanManager = new HumanManager(human);
		
		
	}

	//在加载 信息
	public void load() {
		humanManager.load();
		
	}

}
