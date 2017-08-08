package com.zhiyin.trace.demo.time;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
public class DateService {

    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${remote.server}")
    private String remoteServer;

    @HystrixCommand
    public String getRemoteTime2() {
        Span dateServiceSpan = tracer.createSpan(Const.start);
        try {
            dateServiceSpan.logEvent("start call remote");
            ResponseEntity<String> getResult = restTemplate.getForEntity(URI.create(remoteServer+"/curTime"), String.class);
            dateServiceSpan.logEvent("end call remote");
            return getResult.getBody();
        } catch (Exception e) {
            log.error("get remote time error",e);
        } finally {
            this.tracer.close(dateServiceSpan);
        }
        return "";
    }

    public String getRemoteTime() {
        Span dateServiceSpan = tracer.createSpan(Const.start);
        try {
            dateServiceSpan.logEvent("start call remote");
            ResponseEntity<String> getResult = restTemplate.getForEntity(URI.create(remoteServer + "/curTime"), String.class);
            dateServiceSpan.logEvent("end call remote");
            return getResult.getBody();
        } catch (Exception e) {
            log.error("get remote time error", e);
        } finally {
            this.tracer.close(dateServiceSpan);
        }
        return "";
    }
}