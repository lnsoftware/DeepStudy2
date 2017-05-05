package com.zhiyin.frame.api;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Hide the access to the microservice inside this local service.
 *
 * @author Paul Chapman
 */
@Slf4j
@Service
public class NotificationService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl = "http://DEMO-PROVIDER";

    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show
     * this.
     */
    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        log.warn("The RestTemplate request factory is "
                + restTemplate.getRequestFactory());
    }

//    @HystrixCommand
    public String version( ) {

        String accounts = null;
        try {
            accounts = restTemplate.getForObject(serviceUrl
                    + "/demo-provider/version", String.class );
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return accounts;

    }

}
