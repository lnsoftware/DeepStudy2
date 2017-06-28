package br.com.fabriciodeb.sample.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerInterceptor<T> implements MethodInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new LoggerInterceptor<T>());

        return (T) enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        LOG.info("before method: " + method);

        Object result = proxy.invokeSuper(obj, args);

        LOG.info("after method: " + method);

        return result;
    }

}
