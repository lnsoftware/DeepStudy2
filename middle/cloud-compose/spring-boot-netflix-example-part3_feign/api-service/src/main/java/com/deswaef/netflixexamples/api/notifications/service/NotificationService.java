package com.deswaef.netflixexamples.api.notifications.service;

import com.deswaef.netflixexamples.api.infrastructure.Collaborators;
import com.deswaef.netflixexamples.api.notifications.client.NotificationResource;
import com.deswaef.netflixexamples.api.notifications.client.NotificationVersionResource;
import com.deswaef.netflixexamples.api.notifications.model.Notification;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class NotificationService {

    private final Log LOG = LogFactory.getLog(this.getClass());

    @Autowired
    @Lazy
    private EurekaClient discoveryClient;
    @Autowired
    private NotificationResource notificationResource;
    @Autowired
    private NotificationVersionResource notificationVersionResource;

    @HystrixCommand(groupKey = "tp-notification-service", fallbackMethod = "statusNotFound")
    public InstanceStatus notificationsStatus() {
        return discoveryClient.getNextServerFromEureka(Collaborators.NOTIFICATIONS, false)
            .getStatus();
    }

    public InstanceStatus statusNotFound() {
        return InstanceStatus.DOWN;
    }

    @HystrixCommand(groupKey = "tp-notification-service", fallbackMethod = "notificationsAreDown")
    public Observable<String> statusPageUrl() {
        return Observable.just(
            discoveryClient.getNextServerFromEureka(Collaborators.NOTIFICATIONS, false)
                .getStatusPageUrl()
        );
    }

    @HystrixCommand(groupKey = "tp-notification-service", fallbackMethod = "notificationsAreDown")
    public String version() {
        return notificationVersionResource.version();
    }

    public String notificationsAreDown() {
        return "notificaton service is down";
    }

    public List<Notification> notifications() {
        return notificationResource.findAll();
    }

}
