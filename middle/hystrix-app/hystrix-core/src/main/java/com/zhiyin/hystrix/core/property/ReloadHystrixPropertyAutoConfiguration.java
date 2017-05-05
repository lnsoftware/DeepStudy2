package com.zhiyin.hystrix.core.property;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(JmsAutoConfiguration.class)

public class ReloadHystrixPropertyAutoConfiguration {

    @Bean
    public ReloadHystrixPropertyContextListener executorListener() {
        return new ReloadHystrixPropertyContextListener();
    }


}
