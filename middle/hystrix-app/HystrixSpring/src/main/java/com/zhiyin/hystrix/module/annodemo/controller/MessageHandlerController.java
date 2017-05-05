package com.zhiyin.hystrix.module.annodemo.controller;


import com.zhiyin.hystrix.module.annodemo.entity.Message;
import com.zhiyin.hystrix.module.annodemo.httpservice.MessageHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hg on 2016/1/21.
 */

@Slf4j
@RestController("/anno")
public class MessageHandlerController {

    @Autowired
    private MessageHandlerService messageHandlerService;

    @RequestMapping("/timeout")
    public String simpleHello() {

        log.info("anno test.");

        Message m = new Message();
        m.setDelayBy(2000);
        String message = "Welcome to our App, here is a random value : " + messageHandlerService.handleMessage(m).getReceived();

        return message;
    }

}
