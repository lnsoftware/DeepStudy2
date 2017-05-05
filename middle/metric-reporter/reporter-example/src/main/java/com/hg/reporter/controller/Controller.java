package com.hg.reporter.controller;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.hg.reporter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    MetricRegistry metricRegistry;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String meteredEndpoint() {

        Meter meter = metricRegistry.meter("timetask.");
        meter.mark();
        userService.getUserInfo(1L);
        return "OK";
    }

}
