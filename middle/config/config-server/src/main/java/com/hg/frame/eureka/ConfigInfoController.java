package com.hg.frame.eureka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.jpa.ConfigInfo;
import org.springframework.cloud.config.server.jpa.ConfigInfoRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ConfigInfoController {

    @Autowired
    EnvironmentRepository environmentRepository;

    @Autowired
    private ConfigInfoRepository configInfoRepository;

    @RequestMapping(value = "list/{app}/{profile}")
    public Environment find(@PathVariable("app") String app, @PathVariable("profile") String profile) {
        return environmentRepository.findOne(app, profile, null);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String add(@RequestBody ConfigInfo configInfo) {
        log.info("configInfo:" + JSON.toJSONString(configInfo));
        configInfoRepository.save(configInfo);
        return "OK";
    }

}
