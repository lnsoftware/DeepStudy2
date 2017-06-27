package com.zhiyin.cglib.cando;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

/**
 * Created by hg on 2017/6/26.
 */
public class MethodInterceptorAnalysisTest extends BaseTest {

    @Test
    public void proxyTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DemoService.class);
        enhancer.setCallback(new LogInterceprtor());

        DemoService proxy = (DemoService) enhancer.create();

        String hello = proxy.hello("hg");
    }

}

class DemoService {

    public String hello(String name) {
        return "hello " + name;
    }

}

class LogInterceprtor implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before: " + method);
        Object ret = proxy.invokeSuper(obj, args);
        System.out.println("after: " + method);
        return ret;
    }
}