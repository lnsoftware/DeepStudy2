package com.deswaef.netflixexamples.api.notifications;

import com.deswaef.netflixexamples.api.notifications.model.Notification;
import com.deswaef.netflixexamples.api.notifications.service.NotificationService;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/notifications")
public class NotificationStatusRestController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(method = GET)
    public InstanceStatus status() {
        return notificationService.notificationsStatus();
    }

    @RequestMapping(method = GET, value = "/status")
    public DeferredResult<String> getStatusPageUrl() {
        DeferredResult<String> result = new DeferredResult<>();
        notificationService.statusPageUrl().single()
            .subscribe(
                result::setResult,
                result::setErrorResult
            );
        return result;
    }

    @RequestMapping("/version")
    public String version() {
        return notificationService.version();
    }

    @RequestMapping("/all")
    public List<Notification> findAll() {
        return notificationService.notifications();
    }
}
