package com.deswaef.netflixexamples.notifications.repository;

import com.deswaef.netflixexamples.notifications.model.Notification;
import com.google.common.collect.Sets;
import java.util.Set;
import org.springframework.stereotype.Repository;
import rx.Observable;

@Repository
public class StubNotificationRepository {

    private Set<Notification> notificationList = Sets.newConcurrentHashSet();

    public Observable<Notification> findAll() {
        return Observable.from(notificationList);
    }

    public void save(Notification notification) {
        notificationList.add(notification);
    }

}
