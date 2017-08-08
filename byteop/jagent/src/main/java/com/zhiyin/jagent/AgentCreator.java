package com.zhiyin.jagent;

import net.bytebuddy.agent.builder.AgentBuilder;

public interface AgentCreator {
	public AgentBuilder createAgent(String... agentArguments);
}
