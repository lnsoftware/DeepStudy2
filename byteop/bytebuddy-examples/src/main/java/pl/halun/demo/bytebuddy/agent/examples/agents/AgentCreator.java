package pl.halun.demo.bytebuddy.agent.examples.agents;

import net.bytebuddy.agent.builder.AgentBuilder;

public interface AgentCreator {
	public AgentBuilder createAgent(String... agentArguments);
}
