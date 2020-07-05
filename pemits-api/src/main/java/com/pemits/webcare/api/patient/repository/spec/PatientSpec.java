package com.pemits.webcare.api.patient.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.patient.entity.Patient;

/**
 * @author Elvin Shrestha on 7/5/2020
 */
public class PatientSpec implements Specification<Patient> {

    private final String property;
    private final String value;

    public PatientSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
