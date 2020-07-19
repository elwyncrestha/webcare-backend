package com.pemits.webcare.api.appointment.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.appointment.entity.AppointmentReport;
import com.pemits.webcare.core.repository.BaseSpecBuilder;

/**
 * @author Elvin Shrestha on 7/19/2020
 */
public class AppointmentReportSpecBuilder extends BaseSpecBuilder<AppointmentReport> {

    public AppointmentReportSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<AppointmentReport> getSpecification(String property,
        String filterValue) {
        return new AppointmentReportSpec(property, filterValue);
    }
}
