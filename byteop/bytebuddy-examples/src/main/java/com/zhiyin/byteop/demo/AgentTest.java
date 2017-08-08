package com.zhiyin.byteop.demo;

import static java.lang.System.nanoTime;
import static net.bytebuddy.matcher.ElementMatchers.isSubTypeOf;
import static org.assertj.core.api.Assertions.assertThat;


import com.zhiyin.byteop.demo.advice.ProfiledAdvice;
import com.zhiyin.byteop.demo.agent.ErrorListener;
import com.zhiyin.byteop.demo.agent.ToStringMethodTransformer;
import com.zhiyin.byteop.demo.ttl.MineThreadPoolExecutor;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AgentTest {

	@Before
	public void setUp() throws Exception {
		ByteBuddyAgent.install();
	}

	@Test
	public void testProfileMethod() {

        UserInfo.makeSureClassIsLoaded();

		new AgentBuilder.Default()
				.with(new ErrorListener())
				.with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
				.with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
				.with(AgentBuilder.TypeStrategy.Default.REDEFINE)
				.type(ElementMatchers.named(UserInfo.class.getName()))
				.transform((builder, typeDescription, classLoader) -> builder
						.visit(Advice.to(ProfiledAdvice.class).on(ElementMatchers.any())))
				.installOnByteBuddyAgent();

		new UserInfo().profiledMethod(1);

//		assertEquals("net.bytebuddy.test.TestByteBuddyProfiler$ProfiledClass.profiledMethod", signature);
//		assertEquals(1, returnValue);
//		assertTrue(executionTime > 0);
	}

    @Test
    public void toStringAgentTest() {

        new AgentBuilder.Default()
                .type(isSubTypeOf(Object.class))
                .transform(new ToStringMethodTransformer())
                .installOnByteBuddyAgent();

        String str = new UserInfo().toString();

        assertThat(str).isEqualTo("Hello");
    }

	public static class UserInfo{
		public static void makeSureClassIsLoaded() {
		}

		public int profiledMethod(int i) {
			return i;
		}
	}

}