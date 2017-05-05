package com.zhiyin.bootx.thirdenv.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hg on 2017/2/27.
 */
@Configuration
public class Config {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
       return mapper;
    }

}
