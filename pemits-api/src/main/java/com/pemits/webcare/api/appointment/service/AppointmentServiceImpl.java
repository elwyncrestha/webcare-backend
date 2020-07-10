package com.pemits.webcare.api.appointment.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pemits.webcare.api.appointment.entity.Appointment;
import com.pemits.webcare.api.appointment.repository.AppointmentRepository;
import com.pemits.webcare.api.appointment.repository.spec.AppointmentSpecBuilder;
import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.api.patient.service.PatientService;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.api.user.service.UserService;
import com.pemits.webcare.core.enums.UserType;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
@Service
public class AppointmentServiceImpl extends BaseServiceImpl<Appointment, Long> implements
    AppointmentService {

    private final AppointmentRepository repository;
    private final UserService userService;
    private final PatientService patientService;

    public AppointmentServiceImpl(
        AppointmentRepository repository,
        UserService userService,
        PatientService patientService) {
        super(repository);

        this.repository = repository;
        this.userService = userService;
        this.patientService = patientService;
    }

    @Override
    public Appointment save(Appointment appointment) {
        Patient patient = appointment.getPatient();
        if (null == patient.getId()) {
            User user = patient.getUser();
            user.setUserType(UserType.PATIENT);
            user.setUsername(String.valueOf(System.currentTimeMillis()));
            User savedUser = userService.save(user);
            Patient newPatient = patientService.findByUserId(savedUser.getId());
            newPatient.setAge(patient.getAge());
            Patient savedPatient = patientService.save(newPatient);
            appointment.setPatient(savedPatient);
        }
        return repository.save(appointment);
    }

    @Override
    protected BaseSpecBuilder<Appointment> getSpec(Map<String, String> filterParams) {
        return new AppointmentSpecBuilder(filterParams);
    }
}
