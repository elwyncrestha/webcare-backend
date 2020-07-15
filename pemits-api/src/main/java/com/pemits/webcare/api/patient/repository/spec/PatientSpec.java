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

    private static final String FILTER_BY_USER_ID = "user.id";
    private static final String FILTER_BY_NAME = "user.name";

    private final String property;
    private final String value;

    public PatientSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_USER_ID:
                return criteriaBuilder.equal(root.join("user").get("id"), Long.valueOf(value));
            case FILTER_BY_NAME:
                return criteriaBuilder.like(root.join("user").get("name"), "%" + value.toLowerCase() + "%");
            default:
                return null;
        }
    }
}
