package pl.halun.demo.bytebuddy.agent.examples.agents.hello;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.named;
import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

/**
 * This agent replaces all toString implementations with fixed value
 * "Hello World!"
 *
 */
public class HelloWorldAgent implements AgentCreator {

	@Override
	public AgentBuilder createAgent(String... agentArguments) {
		return new AgentBuilder.Default().type(any()).transform(
				new AgentBuilder.Transformer() {
					@Override
					public DynamicType.Builder<?> transform(
							DynamicType.Builder<?> builder,
							TypeDescription typeDescription,
							ClassLoader classLoader) {

						return builder.method(named("toString")).intercept(
								FixedValue.value("Hello World!"));
					}
				});
	}

}
