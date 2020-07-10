package com.pemits.webcare.api.patient.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.api.patient.repository.PatientRepository;
import com.pemits.webcare.api.patient.repository.spec.PatientSpecBuilder;
import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 7/5/2020
 */
@Service
public class PatientServiceImpl extends BaseServiceImpl<Patient, Long> implements PatientService {

    private final PatientRepository repository;

    protected PatientServiceImpl(
        PatientRepository repository) {
        super(repository);

        this.repository = repository;
    }

    @Override
    protected BaseSpecBuilder<Patient> getSpec(Map<String, String> filterParams) {
        return new PatientSpecBuilder(filterParams);
    }

    @Override
    public Patient findByUserId(long userId) {
        User user = new User();
        user.setId(userId);
        return repository.findPatientByUser(user);
    }
}
