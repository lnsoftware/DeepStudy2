package com.zhiyin.spring.aop.demo4;

import com.zhiyin.spring.aop.demo4.aspect.AnnoAspect;
import com.zhiyin.spring.aop.demo4.aspect.HelloBeforeAdvice;
import com.zhiyin.spring.aop.demo4.service.UserService;
import com.zhiyin.spring.aop.demo4.service.impl.HelloImpl;
import com.zhiyin.spring.aop.demo4.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * Created by wangqinghui on 2018/4/23.
 */
public class AopTest {


    @Test
    public void test() {
        UserService myService = new UserServiceImpl();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(myService);
        proxyFactory.addAspect(AnnoAspect.class);
        //是否需要使用CGLIB代理
        proxyFactory.setProxyTargetClass(true);
        UserService proxy = proxyFactory.getProxy();
        proxy.hello();
    }

    @Test
    public void testAspectJProxyFactory() {
        UserService myService = new UserServiceImpl();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(myService);
        proxyFactory.addAspect(HelloBeforeAdvice.class);
        //是否需要使用CGLIB代理
        proxyFactory.setProxyTargetClass(true);
        UserService proxy = proxyFactory.getProxy();
        proxy.hello();
    }
}
