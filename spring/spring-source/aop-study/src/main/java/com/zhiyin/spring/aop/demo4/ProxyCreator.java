package com.zhiyin.spring.aop.demo4;

import com.google.common.collect.Sets;
import com.zhiyin.spring.aop.demo4.aspect.HelloBeforeAdvice;
import com.zhiyin.spring.aop.demo4.service.IHello;
import com.zhiyin.spring.aop.demo4.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProxyCreator implements BeanPostProcessor, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ProxyCreator.class);


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
            proxyFactory.addAdvice( new HelloBeforeAdvice() );
            return proxyFactory.getProxy();
        }


        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}