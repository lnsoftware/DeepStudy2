package com.hg.dubbo.study.javassist;

import com.alibaba.dubbo.common.bytecode.Proxy;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hg on 2016/8/30.
 */
public class JavassistProxyTest {

    public static void main(String[] args) {

        final Proxy proxy = Proxy.getProxy(DemoService.class);

//        Invoker<DemoService> invoker = new DubboInvoker<DemoService>(null, null, null);
//        DemoService demoService = (DemoService) spi.newInstance(new InvokerInvocationHandler(invoker));

        final InvocationHandler handler = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("invoke method: " + method.getName());
                return null;
            }};

            DemoService demoService = (DemoService) proxy.newInstance( handler );

        demoService.sayHello("ss");

        System.out.println();
        }
}
