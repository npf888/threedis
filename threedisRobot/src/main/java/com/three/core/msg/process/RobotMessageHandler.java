package com.three.core.msg.process;

import org.springframework.stereotype.Component;

import com.three.core.msg.inter.IMessage;
@Component
public interface RobotMessageHandler {

	void handler(IMessage msg);
}
