package pl.halun.demo.bytebuddy.agent.examples.agents.trycatch;

import static net.bytebuddy.matcher.ElementMatchers.named;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.MethodDelegation;
import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;

/**
 * This agent encloses method invocation into try-catch block, logs any caught
 * exception and then re-throws it.
 *
 */
public class TryCatchAgent implements AgentCreator {

	@Override
	public AgentBuilder createAgent(String... agentArguments) {
		if (agentArguments.length != 3) {
			throw new IllegalArgumentException(
					"Missing agent parameters: class name, method name");
		}
		
		return new AgentBuilder.Default().type(named(agentArguments[1]))
				.transform(new AgentBuilder.Transformer() {
					
					@Override
					public Builder<?> transform(Builder<?> builder,
							TypeDescription typeDescription,
							ClassLoader classLoader) {
						
						return builder.method(named(agentArguments[2]))
								.intercept(
										MethodDelegation
												.to(TryCatchInterceptor.class));
					}
				});
	}
}
