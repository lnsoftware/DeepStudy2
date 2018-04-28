package com.zhiyin.spring.aop.demo4;

import com.zhiyin.spring.aop.demo4.aspect.HelloAfterAdvice;
import com.zhiyin.spring.aop.demo4.aspect.HelloBeforeAdvice;
import com.zhiyin.spring.aop.demo4.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class ProxyCreator2 implements BeanPostProcessor, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ProxyCreator2.class);


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return createProxy(bean);
    }

    /**
     * create oceanus proxy
     *
     * @param bean
     * @return
     */
    protected Object createProxy(Object bean) {
        Class targetClass = AopUtils.getTargetClass(bean);

        if(bean instanceof UserService){
            AopUtils.getTargetClass(bean);
            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setProxyTargetClass(true);
            proxyFactory.setTarget(bean);
            proxyFactory.setInterfaces(UserService.class);
            proxyFactory.addAdvice( new HelloAfterAdvice() );
            return proxyFactory.getProxy();
        }


        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}