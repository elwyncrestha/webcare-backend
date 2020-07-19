package com.pemits.webcare.api.appointment.repository;

import org.springframework.stereotype.Repository;

import com.pemits.webcare.api.appointment.entity.AppointmentReport;
import com.pemits.webcare.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 7/19/2020
 */
@Repository
public interface AppointmentReportRepository extends BaseRepository<AppointmentReport, Long> {

}
