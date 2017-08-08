package pl.halun.demo.bytebuddy.agent.examples;

import java.lang.instrument.Instrumentation;

import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;

public class AgentInstaller {

	private static final String ERR_AGENT_NAME_NOT_PROVIDED = "Agent name must be provided.";
	private static final String ERR_UNKOWN_AGENT = "Agent with provided name does not exist.";
	
	/**
	 * Installation of selected java agent from command line.
	 *
	 * @param agentArguments
	 *            arguments for agent
	 * @param instrumentation
	 *            instrumentation instance
	 */
	public static void premain(String agentArguments,
			Instrumentation instrumentation) {
		if (agentArguments == null || agentArguments.isEmpty()) {
			System.err.println(ERR_AGENT_NAME_NOT_PROVIDED);
			System.exit(-1);
		}
		
		String[] arguments = agentArguments.split("\\s+?");
		AgentCreator agentCreator = DefinedAgents.getAgentCreatorFor(arguments[0]);
		if (agentCreator == null) {
			System.err.println(ERR_UNKOWN_AGENT);
			System.exit(-1);
		}
		
		agentCreator.createAgent(arguments).installOn(instrumentation);
	}
	
}
