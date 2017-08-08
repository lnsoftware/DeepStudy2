package pl.halun.demo.bytebuddy.agent.examples.agents.regexlogger;

import static net.bytebuddy.matcher.ElementMatchers.nameMatches;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;

/**
 * Adds logging to a class and its method selected using regular expressions.
 *
 */
public class RegexSelectionLoggerAgent implements AgentCreator {

	@Override
	public AgentBuilder createAgent(String... agentArguments) {
		if (agentArguments.length != 3) {
			throw new IllegalArgumentException("Missing agent parameters: class name regex, method name regex");
		}

		return new AgentBuilder.Default().type(nameMatches(agentArguments[1]))
				.transform(new AgentBuilder.Transformer() {

					@Override
					public Builder<?> transform(Builder<?> builder, TypeDescription type, ClassLoader classLoader) {
						return builder.method(nameMatches(agentArguments[2])).intercept(
								MethodDelegation.to(LoggingInterceptor.class).andThen(SuperMethodCall.INSTANCE));
					}
				});
	}

}
