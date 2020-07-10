package com.pemits.webcare.api.patient.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.patient.entity.Patient;
import com.pemits.webcare.core.repository.BaseSpecBuilder;

/**
 * @author Elvin Shrestha on 7/5/2020
 */
public class PatientSpecBuilder extends BaseSpecBuilder<Patient> {

    public PatientSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Patient> getSpecification(String property, String filterValue) {
        return new PatientSpec(property, filterValue);
    }
}
