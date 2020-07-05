package com.pemits.webcare.api.appointment.repository.spec;

import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
public class AppointmentSpecBuilder extends BaseSpecBuilder<Appointment> {

    public AppointmentSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Appointment> getSpecification(String property, String filterValue) {
        return new AppointmentSpec(property, filterValue);
    }
}
