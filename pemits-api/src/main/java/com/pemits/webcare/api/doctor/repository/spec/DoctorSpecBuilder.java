package com.pemits.webcare.api.doctor.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.doctor.entity.Doctor;
import com.pemits.webcare.core.repository.BaseSpecBuilder;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
public class DoctorSpecBuilder extends BaseSpecBuilder<Doctor> {

    public DoctorSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Doctor> getSpecification(String property, String filterValue) {
        return new DoctorSpec(property, filterValue);
    }
}
