package com.zhiyin.spring.bpr;

import com.zhiyin.spring.service.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            log.info("beforeInitialization");
        }
        return bean;
    }

    // bean初始化后，会调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof User) {
            log.info("afterInitialization");
            ((User) bean).setSystemMark("postProcessAfterInitialization");
        }
        return bean;
    }

}