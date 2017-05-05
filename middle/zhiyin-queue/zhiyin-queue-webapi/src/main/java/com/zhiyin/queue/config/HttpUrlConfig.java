package com.zhiyin.queue.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:config/http-service-url.properties")
public class HttpUrlConfig {

    private @Value("${contents.selById}") String contentsSelById;

}