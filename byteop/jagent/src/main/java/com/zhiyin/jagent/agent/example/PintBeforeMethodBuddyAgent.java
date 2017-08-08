package com.jagent.agent.example;//package com.zhiyin.jagent.agent.example;
//
//import net.bytebuddy.agent.builder.AgentBuilder;
//import net.bytebuddy.description.type.TypeDescription;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.implementation.FixedValue;
//import pl.halun.demo.bytebuddy.agent.examples.agents.AgentCreator;
//
//import static net.bytebuddy.matcher.ElementMatchers.any;
//import static net.bytebuddy.matcher.ElementMatchers.nameMatches;
//import static net.bytebuddy.matcher.ElementMatchers.named;
//
///**
// * This agent replaces all toString implementations with fixed value
// * "Hello World!"
// *
// */
//public class PintBeforeMethodBuddyAgent implements AgentCreator {
//
//	@Override
//	public AgentBuilder createAgent(String... agentArguments) {
//		return new AgentBuilder.Default().type(any()).transform(
//				new AgentBuilder.Transformer() {
//					@Override
//					public DynamicType.Builder<?> transform(
//							DynamicType.Builder<?> builder,
//							TypeDescription typeDescription,
//							ClassLoader classLoader) {
//
//						return builder.method(nameMatches("com.zhiyin.*")).intercept()
//					}
//				});
//	}
//
//}
