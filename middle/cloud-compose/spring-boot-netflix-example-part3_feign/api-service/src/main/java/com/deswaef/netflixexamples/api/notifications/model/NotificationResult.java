package com.deswaef.netflixexamples.api.notifications.model;

import java.util.List;

public class NotificationResult {
    List<Notification> notifications;

    public List<Notification> getNotifications() {
        return notifications;
    }

    public NotificationResult setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }
}
