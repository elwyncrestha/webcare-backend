package com.pemits.webcare.api.appointment.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pemits.webcare.api.appointment.entity.AppointmentReport;
import com.pemits.webcare.api.appointment.repository.AppointmentReportRepository;
import com.pemits.webcare.api.appointment.repository.spec.AppointmentReportSpecBuilder;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 7/19/2020
 */
@Service
public class AppointmentReportServiceImpl extends BaseServiceImpl<AppointmentReport, Long> implements AppointmentReportService {

    protected AppointmentReportServiceImpl(
        AppointmentReportRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<AppointmentReport> getSpec(Map<String, String> filterParams) {
        return new AppointmentReportSpecBuilder(filterParams);
    }
}
