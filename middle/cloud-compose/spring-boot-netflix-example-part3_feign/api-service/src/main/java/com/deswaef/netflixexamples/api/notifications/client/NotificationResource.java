package com.deswaef.netflixexamples.api.notifications.client;

import com.deswaef.netflixexamples.api.notifications.model.Notification;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "http://notification-service", fallback = NotificationResourceImpl.class)
public interface NotificationResource {

    @RequestMapping(value = "/notifications", method = GET)
    List<Notification> findAll();

}
