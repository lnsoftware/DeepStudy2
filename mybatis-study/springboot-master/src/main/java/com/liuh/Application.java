package com.liuh;

/**
 * Created by liuhui.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    /**
     * springboot主函数
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 配置读取
     * @param builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * 端口设置
     * @param container
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }

    /**
     * 线程池
     */
    @Bean
    protected ThreadPoolTaskExecutor mvcTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("my-mvc-task-executor-");
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(30);
        return taskExecutor;
    }
}
