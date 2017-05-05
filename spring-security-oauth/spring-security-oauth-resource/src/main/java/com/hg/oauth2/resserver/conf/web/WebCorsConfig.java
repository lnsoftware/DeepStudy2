package com.hg.oauth2.resserver.conf.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 解决js跨域问题
 */
@Configuration
@EnableWebMvc
public class WebCorsConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "PUT", "DELETE", "GET")
//            .allowedHeaders("header1", "header2", "header3")
//            .exposedHeaders("header1", "header2")
                .allowCredentials(false).maxAge(3600);
    }
}