package com.zhiyin.spring.aop.demo4;

import com.zhiyin.spring.aop.demo4.aspect.HelloBeforeAdvice;
import com.zhiyin.spring.aop.demo4.service.IHello;
import com.zhiyin.spring.aop.demo4.service.impl.HelloImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by hg on 2017/6/15.
 */
public class ProxyFactoryTest {

    public static void main(String[] args) {

        IHello hello = new HelloImpl();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setTarget(hello);
        proxyFactory.setInterfaces(IHello.class);
        proxyFactory.addAdvice( new HelloBeforeAdvice() );

        IHello proxy = (IHello) proxyFactory.getProxy();
        proxy.hello("sss");
    }
}
