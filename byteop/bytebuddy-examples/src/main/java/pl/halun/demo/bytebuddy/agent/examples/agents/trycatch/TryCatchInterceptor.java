package pl.halun.demo.bytebuddy.agent.examples.agents.trycatch;

import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryCatchInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(TryCatchInterceptor.class);

	@RuntimeType
	public static Object intercept(@SuperCall Callable<?> superCall,
			@RuntimeType Object value) throws Exception {
		try {
			return superCall.call();
		} catch (Exception e) {
			logger.error("Exception while executing intercepted method", e);
			throw e;
		}
	}

	@RuntimeType
	public static Object interceptNoParam(@SuperCall Callable<?> superCall)
			throws Exception {
		try {
			return superCall.call();
		} catch (Exception e) {
			logger.error("Exception while executing intercepted method", e);
			throw e;
		}
	}

	public static void interceptVoidWithParameter(
			@SuperCall Callable<?> superCall, @RuntimeType Object value)
			throws Exception {
		try {
			superCall.call();
		} catch (Exception e) {
			logger.error("Exception while executing intercepted method", e);
			throw e;
		}
	}

	public static void interceptVoid(@SuperCall Callable<?> superCall)
			throws Exception {
		try {
			superCall.call();
		} catch (Exception e) {
			logger.error("Exception while executing intercepted method", e);
			throw e;
		}
	}

}
