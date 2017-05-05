package net.xiayule.mockito;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tan on 16-1-5.
 */
public class Mockito {
    private static Map<Invocation, Return> results = new HashMap<Invocation, Return>();

    private static Invocation lastInvocation;

    public static <T> T mock(Class<T> clazz) {
        return createProxy(clazz, new EmptyValueAnswer());
    }

    public static <T> T spy(Class<T> clazz) {
        return createProxy(clazz, new CallRealMethodAnser());
    }


    private static <T> T createProxy(Class<T> clazz, Answer defaultAnswer) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MockInterceptor(defaultAnswer));
        return (T) enhancer.create();
    }

    private static class MockInterceptor implements MethodInterceptor {

        private Answer defaultAnswer;

        public MockInterceptor(Answer defaultAnswer) {
            this.defaultAnswer = defaultAnswer;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            Invocation invocation = new Invocation(obj, method, args, proxy);

            lastInvocation = invocation;

            if (results.containsKey(invocation)) {
                return results.get(invocation).answer(invocation);
            }

            return defaultAnswer.answer(invocation);
        }
    }

    public static <T> When<T> when(T o) {
        return new When<T>();
    }

    public static class When<T> {
        public void thenReturn(T retObj) {
            results.put(lastInvocation, new Return(retObj));
        }
    }
}
