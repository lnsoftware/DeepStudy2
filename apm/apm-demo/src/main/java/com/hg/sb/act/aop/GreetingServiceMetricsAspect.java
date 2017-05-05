package com.hg.sb.act.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


/**
 * 通过切面方式统计接口调用次数
 */
@Aspect
@Component
class GreetingServiceMetricsAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceMetricsAspect.class);
    private final CounterService counterService;

    //    @Autowired
    private GaugeService gaugeService;

//    @Autowired
//    public GreetingServiceMetricsAspect(CounterService counterService) {
//        this.counterService = counterService;
//    }

    @Autowired
    public GreetingServiceMetricsAspect(CounterService counterService, GaugeService gaugeService) {
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @AfterReturning(pointcut = "execution(* com.hg.sb.act.service.greeting.GreetingService.getGreeting(int)) && args(number)", argNames = "number")
    public void afterCallingGetGreeting(int number) {
        System.out.println("ssss");
        LOGGER.debug("Triggered after calling getGreeting()");
        counterService.increment("counter.calls.get_greeting");
        counterService.increment("counter.calls.get_greeting." + number);
        gaugeService.submit("comsume_time", 12.2);
    }

    @AfterThrowing(pointcut = "execution(* com.hg.sb.act.service.greeting.GreetingService.getGreeting(int))", throwing = "e")
    public void afterGetGreetingThrowsException(NoSuchElementException e) {
        LOGGER.debug("Triggered after getGreeting() throws exception");
        counterService.increment("counter.errors.get_greeting");
    }

}
