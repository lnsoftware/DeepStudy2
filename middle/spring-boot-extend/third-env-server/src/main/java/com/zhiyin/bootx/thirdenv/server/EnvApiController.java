package com.zhiyin.bootx.thirdenv.server;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by hg on 2017/2/27.
 */
@Slf4j
@RestController
public class EnvApiController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/trenv/config")
    public String config(){
        try {
            HashMap commonMap = objectMapper.readValue(new File(this.getClass().getResource("/META-INF/public-default.yml").getPath()), HashMap.class);
            Properties properties = objectMapper.readValue(new File(this.getClass().getResource("/test-properties.yml").getPath()), Properties.class);

            properties.putAll(commonMap);
            return JSON.toJSONString(properties);

        } catch (IOException e) {
            log.error("load yml file error",e);
        }
        return "";
    }
}
