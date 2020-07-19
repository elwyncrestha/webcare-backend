package com.pemits.webcare.api.appointment.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.appointment.entity.AppointmentReport;

/**
 * @author Elvin Shrestha on 7/19/2020
 */
public class AppointmentReportSpec implements Specification<AppointmentReport> {

    private static final String FILTER_BY_APPOINTMENT_ID = "appointment.id";

    private final String property;
    private final String value;

    public AppointmentReportSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<AppointmentReport> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_APPOINTMENT_ID:
                return criteriaBuilder
                    .equal(root.join("appointment").get("id"), Long.valueOf(value));
            default:
                return null;
        }
    }
}
