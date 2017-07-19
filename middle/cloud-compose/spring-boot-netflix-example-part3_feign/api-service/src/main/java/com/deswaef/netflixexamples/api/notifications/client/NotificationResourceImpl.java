package com.deswaef.netflixexamples.api.notifications.client;

import com.deswaef.netflixexamples.api.notifications.model.Notification;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NotificationResourceImpl implements NotificationResource {

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>();
    }
}
