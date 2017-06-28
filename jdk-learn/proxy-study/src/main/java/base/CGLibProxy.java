package base;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by Administrator on 2016/7/12.
 */
public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy proxy = new CGLibProxy();

    private CGLibProxy() {
    }

    public static CGLibProxy getInstance() {
        return proxy;
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        begin();
        Object result = proxy.invokeSuper(obj, args);
        end();
        return result;
    }

    public void begin() {
        System.out.println("##begin##");
    }

    public void end() {
        System.out.println("##end##");
    }
}