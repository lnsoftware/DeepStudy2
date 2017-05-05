package com.zhiyin.trace.demo;

import com.zhiyin.trace.demo.service.DateService;
import com.zhiyin.trace.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
public class SleuthDemoApplication {
    
    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DateService dateService;

    @Autowired
    private LogService logService;

    @Value("${server.port}")
    private int appPort;

    @Value("${otherapp.port}")
    private int otherappPort;



    @RequestMapping("/")
    public String home() {
        log.info("Request to / endpoint");
        return "Hello World";
    }

    @RequestMapping("/getRemoteTime")
    public String getRemoteTime() throws URISyntaxException {
        log.info("Request to /getRemoteTime endpoint");

        logService.log("Will call remote service");

        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI("http://localhost:"+otherappPort+"/getTime"), String.class);
        log.info("Got response code: {}", forEntity.getStatusCode().toString());
        return "The remote time is: " + forEntity.getBody();
    }

    @RequestMapping("/getLocalTime")
    public String getLocalTime() throws URISyntaxException {
        log.info("Request to /getLocalTime endpoint");

        logService.log("Will call local service");

        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI("http://localhost:"+appPort+"/getTime"), String.class);
        log.info("Got response code: {}", forEntity.getStatusCode().toString());
        return "The localtime is: " + forEntity.getBody();
    }

    @RequestMapping("/getTime")
    public String getTime() {
        log.info("Request to /getTime endpoint");
        return dateService.getTimeAsString();
    }


}
