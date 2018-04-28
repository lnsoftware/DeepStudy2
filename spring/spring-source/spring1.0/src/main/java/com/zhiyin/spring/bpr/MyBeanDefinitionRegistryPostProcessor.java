//package com.zhiyin.spring.bpr;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//
//public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
//    // 这个方法来自 BeanDefinitionRegistryPostProcessor
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        System.out.println("postProcessBeanDefinitionRegistry");
//    }
//    // 这个方法来自 BeanFactoryPostProcessor
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("postProcessBeanFactory");
//    }
//}