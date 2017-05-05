package com.zhiyin.hds.controller;

import com.netflix.config.ConfigurationManager;
import com.zhiyin.hds.service.SleepServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hg on 2016/10/25.
 */

@Slf4j
@RestController
public class SleepController {

    @Autowired
    SleepServiceImpl sleepService;

    @RequestMapping(method = RequestMethod.GET, path = "/sleep", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String greeting() {
        int sleep = RandomUtils.nextInt(850, 950);
        log.info("sleep:{}", sleep);
        return sleepService.sleep(sleep);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello() {
        sleepService.hello();
        return "";
    }



    @RequestMapping(method = RequestMethod.GET, path = "/ok" )
    public String ok() {
        return "ok";
    }

}
