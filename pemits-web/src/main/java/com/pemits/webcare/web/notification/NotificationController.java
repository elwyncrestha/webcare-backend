package com.pemits.webcare.web.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.api.notification.service.NotificationService;
import com.pemits.webcare.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@RestController
@RequestMapping(NotificationController.URL)
@Slf4j
public class NotificationController extends BaseController<Notification, Long> {

    static final String URL = "/v1/notification";
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        super(notificationService, log.getClass());

        this.notificationService = notificationService;
    }
}
