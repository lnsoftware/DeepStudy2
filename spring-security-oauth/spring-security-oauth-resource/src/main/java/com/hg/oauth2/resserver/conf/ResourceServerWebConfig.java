package com.hg.oauth2.resserver.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
@ComponentScan({"com.hg.oauth2.resserver.web.controller"})
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {

}
