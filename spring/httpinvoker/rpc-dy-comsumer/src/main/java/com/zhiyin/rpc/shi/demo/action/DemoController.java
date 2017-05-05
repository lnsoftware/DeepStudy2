package com.zhiyin.rpc.shi.demo.action;


import com.zhiyin.rpc.httpinvoker.demo.service.DemoService;
import com.zhiyin.rpc.httpinvoker.demo.service.IThreadLocalService;
import com.zhiyin.rpc.httpinvoker.demo.service.TimeConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private TimeConsumeService timeConsumeService;
    @Autowired
    private IThreadLocalService tlService;

    @RequestMapping(value = "/rpc", method = RequestMethod.GET)
    public String prc( ) {

        return demoService.getName();
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public String time( ) {

        return timeConsumeService.timeConsume();
    }
    @RequestMapping(value = "/tl", method = RequestMethod.GET)
    public String tlService( ) {

        return tlService.testPassParm();
    }



}