package com.pemits.webcare.api.notification.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.api.notification.repository.NotificationRepository;
import com.pemits.webcare.api.notification.repository.spec.NotificationSpecBuilder;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@Service
public class NotificationServiceImpl extends BaseServiceImpl<Notification, Long> implements
    NotificationService {

    protected NotificationServiceImpl(
        NotificationRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<Notification> getSpec(Map<String, String> filterParams) {
        return new NotificationSpecBuilder(filterParams);
    }
}
