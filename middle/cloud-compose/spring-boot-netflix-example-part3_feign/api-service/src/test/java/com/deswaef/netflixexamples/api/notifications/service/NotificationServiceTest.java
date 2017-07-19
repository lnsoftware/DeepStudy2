package com.deswaef.netflixexamples.api.notifications.service;

import com.deswaef.netflixexamples.api.notifications.client.NotificationResource;
import com.deswaef.netflixexamples.api.notifications.client.NotificationVersionResource;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.deswaef.netflixexamples.api.infrastructure.Collaborators.NOTIFICATIONS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

    public static final String VERSION = "1.0.0";
    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private EurekaClient discoveryClient;
    @Mock
    private NotificationResource notificationResource;
    @Mock
    private NotificationVersionResource notificationVersionResource;

    @Test
    public void notificationStatusCallsEurekaClient() {
        InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder()
            .setAppName(NOTIFICATIONS)
            .setStatus(InstanceInfo.InstanceStatus.STARTING)
            .build();
        when(discoveryClient.getNextServerFromEureka(NOTIFICATIONS, false))
            .thenReturn(instanceInfo);

        assertThat(notificationService.notificationsStatus())
            .isEqualTo(instanceInfo.getStatus());
    }

    @Test
    public void versionCallsRestTemplate() {
        when(notificationVersionResource.version())
            .thenReturn(VERSION);

        assertThat(notificationService.version())
            .isEqualTo(VERSION);
    }
}