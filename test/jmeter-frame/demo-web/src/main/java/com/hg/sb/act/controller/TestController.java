package com.hg.sb.act.controller;

import com.alibaba.fastjson.JSON;
import com.hg.sb.act.entity.TestC2s;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public String ok() {

        return "ok";
    }

    @RequestMapping(value = "/getinfo", method = RequestMethod.GET)
    public String hello() {

        TestC2s c2s = new TestC2s();
        c2s.setId("1");
        c2s.setName("hello");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(c2s);
    }


    @RequestMapping(value = "/postinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String hello(@RequestBody TestC2s c2s) {
        log.info(JSON.toJSONString(c2s));
        return JSON.toJSONString(c2s);
    }


}
