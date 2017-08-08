package com.zhiyin.jagent;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScanAllAgents {

	private static final Map<String, Class<? extends AgentCreator>> agents = new HashMap<String, Class<? extends AgentCreator>>();

	static {
		Reflections reflections = new Reflections(
				"com.zhiyin.jagent");

		Set<Class<? extends AgentCreator>> subTypes = reflections
				.getSubTypesOf(AgentCreator.class);

		for (Class<? extends AgentCreator> subType : subTypes) {
			agents.put(subType.getSimpleName().toLowerCase(),subType);
		}
	}

	public static AgentCreator getAgentCreatorFor(String agentName) {
		Class<? extends AgentCreator> agentCreatorClass = agents.get(agentName
				.toLowerCase());

		if (agentCreatorClass == null) {
			return null;
		}

		try {
			return agentCreatorClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}
}
