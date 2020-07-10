package com.pemits.webcare.api.appointment.repository;

import org.springframework.stereotype.Repository;

import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.core.repository.BaseRepository;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
@Repository
public interface AppointmentRepository extends BaseRepository<Appointment, Long> {

}
