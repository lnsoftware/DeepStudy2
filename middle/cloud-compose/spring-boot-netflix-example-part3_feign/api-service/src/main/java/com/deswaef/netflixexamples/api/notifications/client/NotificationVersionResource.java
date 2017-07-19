package com.deswaef.netflixexamples.api.notifications.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient("http://notification-service")
public interface NotificationVersionResource {

    @RequestMapping(value = "/version", method = GET)
    String version();

}
