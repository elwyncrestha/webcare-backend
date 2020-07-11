package com.pemits.webcare.api.notification.repository;

import org.springframework.stereotype.Repository;

import com.pemits.webcare.api.notification.entity.Notification;
import com.pemits.webcare.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 7/11/2020
 */
@Repository
public interface NotificationRepository extends BaseRepository<Notification, Long> {

}
