package com.zhiyin.spring.ioc.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanAppConfig {
    
    @Bean(name = "carFactoryBean")
    public CarFactoryBean toolFactory() {
        CarFactoryBean factory = new CarFactoryBean();
        factory.setName("name");
        return factory;
    }
}