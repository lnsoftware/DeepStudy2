package pl.halun.demo.bytebuddy.agent.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;

public class DefinedAgents {

	private static final Map<String, Class<? extends AgentCreator>> agents = new HashMap<String, Class<? extends AgentCreator>>();

	static {
		Reflections reflections = new Reflections(
				"pl.halun.demo.bytebuddy.agent.examples.agents");

		Set<Class<? extends AgentCreator>> subTypes = reflections
				.getSubTypesOf(AgentCreator.class);

		subTypes.forEach(clazz -> agents.put(clazz.getSimpleName()
				.toLowerCase(), clazz));
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
