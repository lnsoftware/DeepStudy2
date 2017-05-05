package com.hg.sb.act.controller;

import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MetricsController {


    @Timed
    @RequestMapping("/time/metric")
    public String tMetric() {
        return "metrics time, view log.";
    }

}
