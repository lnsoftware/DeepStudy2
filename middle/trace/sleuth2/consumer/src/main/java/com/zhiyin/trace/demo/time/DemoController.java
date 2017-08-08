package com.zhiyin.trace.demo.time;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private DateService dateService;

    @Autowired
    private Tracer tracer;

    @RequestMapping("/")
    public String home() {
        return "I'm consumer.";
    }

    @RequestMapping("/getTime")
    public String getRemoteTime() throws URISyntaxException {
        Span dateServiceSpan = tracer.createSpan(Const.before);
        String ret = dateService.getRemoteTime();
        tracer.close(dateServiceSpan);
        return ret;
    }

    @RequestMapping("/getTime2")
    public String getRemoteTime2() throws URISyntaxException {
        Span dateServiceSpan = tracer.createSpan(Const.start);
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CallRemoteTime"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CallRemoteTime"));
        HystrixCommand<String> command = new HystrixCommand<String>(setter) {
            @Override
            protected String run() throws Exception {
                return dateService.getRemoteTime2();
            }
        };
        tracer.close(dateServiceSpan);
        return command.execute();
    }
}
