package com.pemits.webcare.api.notification.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.entity.BaseEntity;
import com.pemits.webcare.core.enums.NotificationStatus;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseEntity<Long> {

    @OneToOne
    private User from;

    @OneToOne
    private User to;

    private String message;

    private NotificationStatus status;
}
