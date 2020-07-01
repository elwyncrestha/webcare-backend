package com.pemits.webcare.api.doctor.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.api.doctor.repository.DoctorRepository;
import com.pemits.webcare.api.doctor.repository.spec.DoctorSpecBuilder;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
@Service
public class DoctorServiceImpl extends BaseServiceImpl<Doctor, Long> implements DoctorService {

    private final DoctorRepository repository;

    protected DoctorServiceImpl(
        DoctorRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected BaseSpecBuilder<Doctor> getSpec(Map<String, String> filterParams) {
        return new DoctorSpecBuilder(filterParams);
    }
}
