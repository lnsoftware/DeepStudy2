package com.mo.test.controller;

import com.mo.test.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MoXingwang on 2017/6/13.
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private TransactionalService transactionalService;

    @RequestMapping(value = "1", method = RequestMethod.GET)
    public String test1() {
        transactionalService.onTransactional();
        return "";
    }

    @RequestMapping(value = "2", method = RequestMethod.GET)
    public String test2() {
        transactionalService.noTransactional();
        return "";
    }

    @RequestMapping(value = "3", method = RequestMethod.GET)
    public String test3() {
        transactionalService.on2noTransactional();
        return "";
    }

    @RequestMapping(value = "4", method = RequestMethod.GET)
    public String test4() {
        transactionalService.no2onTransactional();
        return "";
    }

    @RequestMapping(value = "5", method = RequestMethod.GET)
    public String test5() {
        transactionalService.no2noTransactional();
        return "";
    }

    @RequestMapping(value = "6", method = RequestMethod.GET)
    public String test6() {
        transactionalService.otherServiceTransactional();
        return "";
    }
}
