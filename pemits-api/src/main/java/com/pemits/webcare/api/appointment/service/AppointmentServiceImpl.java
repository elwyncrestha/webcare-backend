package com.pemits.webcare.api.appointment.service;

import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.api.appointment.repository.spec.AppointmentSpecBuilder;
import com.pemits.webcare.core.repository.BaseRepository;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
@Service
public class AppointmentServiceImpl extends BaseServiceImpl<Appointment, Long> implements AppointmentService {

    public AppointmentServiceImpl(BaseRepository<Appointment, Long> repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<Appointment> getSpec(Map<String, String> filterParams) {
        return new AppointmentSpecBuilder(filterParams);
    }
}
