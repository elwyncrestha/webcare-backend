package com.pemits.webcare.api.notification.component;

import static com.pemits.webcare.core.constant.AppConstant.SOCKET_SUBSCRIBER;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.api.notification.service.NotificationService;
import com.pemits.webcare.api.user.service.UserService;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@Component
public class NotificationComponent {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationComponent(
        SimpMessagingTemplate simpMessagingTemplate,
        NotificationService notificationService,
        UserService userService
    ) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public Notification sendAndSaveMessage(Notification notification) {
        Long toId = notification.getTo().getId();
        String destination = String.format("%s/%s", SOCKET_SUBSCRIBER, toId);
        Notification saved = notificationService.save(notification);
        simpMessagingTemplate.convertAndSend(destination, notification);
        return saved;
    }
}
