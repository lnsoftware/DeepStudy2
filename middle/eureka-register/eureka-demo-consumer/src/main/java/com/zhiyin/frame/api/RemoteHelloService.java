package com.zhiyin.frame.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class RemoteHelloService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://DEMO-PROVIDER";

    public String hello( ) {

        String ret = null;
        try {
            ret = restTemplate.getForObject(serviceUrl
                    + "/demo-provider/hello", String.class );
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
