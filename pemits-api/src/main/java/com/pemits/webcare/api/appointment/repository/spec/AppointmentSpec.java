package com.pemits.webcare.api.appointment.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.appointment.entity.Appointment;

/**
 * @Author Mohammad Hussain
 * created on 7/5/2020
 */
public class AppointmentSpec implements Specification<Appointment> {

    private static final String FILTER_BY_PATIENT_ID = "patient.id";

    private final String property;
    private final String value;

    public AppointmentSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_PATIENT_ID:
                return criteriaBuilder.equal(root.join("patient").get("id"), Long.valueOf(value));
        }
        return null;
    }
}
