package com.pemits.webcare.api.notification.repository.spec;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.core.enums.NotificationStatus;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
public class NotificationSpec implements Specification<Notification> {

    public static final String FILTER_BY_TO_ID = "to";
    public static final String FILTER_BY_STATUS = "status";

    private final String property;
    private final String value;

    public NotificationSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Notification> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_TO_ID:
                return criteriaBuilder
                    .equal(root.get(FILTER_BY_TO_ID).get("id"), Long.valueOf(value));
            case FILTER_BY_STATUS:
                return criteriaBuilder
                    .equal(root.get(FILTER_BY_STATUS), NotificationStatus.valueOf(value));
            default:
                return null;
        }
    }

}
