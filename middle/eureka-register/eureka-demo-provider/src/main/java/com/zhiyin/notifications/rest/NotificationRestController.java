package com.zhiyin.notifications.rest;

import com.zhiyin.notifications.model.Notification;
import com.zhiyin.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.functions.Action1;

import java.util.List;

@RestController
@RequestMapping("/")
public class NotificationRestController {

    @Value("${spring.application.version:0.0.0}")
    private String version;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/version")
    public ResponseEntity<String> version() {
        notificationService.create("Someone accessed notification-service: /version");
        return ResponseEntity.ok(version);
    }

//    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
//    public DeferredResult<List<Notification>> notifications() {
//        final DeferredResult<List<Notification>> notificationDeferredResult = new DeferredResult<>();
//        notificationService
//                .findAll()
//                .toList()
//                .subscribe(new Action1<List<Notification>>() {
//                    @Override
//                    public void call(List<Notification> notifications) {
//                        notificationDeferredResult.setResult(notifications);
//                    }
//                });
//
//        return notificationDeferredResult;
//    }
}
