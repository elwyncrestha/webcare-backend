package com.pemits.webcare.api.notification.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.core.repository.BaseSpecBuilder;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
public class NotificationSpecBuilder extends BaseSpecBuilder<Notification> {

    public NotificationSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Notification> getSpecification(String property, String filterValue) {
        return new NotificationSpec(property, filterValue);
    }
}
