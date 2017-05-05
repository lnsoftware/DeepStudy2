package com.zhiyin.mpush.controller;

import com.alibaba.fastjson.JSON;
import com.codahale.metrics.annotation.Timed;
import com.zhiyin.mpush.service.ITestBasicDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private ITestBasicDataService testBasicDataService;

    @RequestMapping("/testok")
    public String hello() {

        log.info("ok");
        return "ok";
    }

//    @Timed
    @RequestMapping("/tmetric")
    public String tMetric() {
        return "find time in log file.";
    }


    @RequestMapping(value = "/testpost" , method = RequestMethod.POST)
    public String hello(TestC2s c2s) {

        log.info(JSON.toJSONString(c2s));

        return JSON.toJSONString(c2s);
    }

    class TestC2s {
        String id ;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
