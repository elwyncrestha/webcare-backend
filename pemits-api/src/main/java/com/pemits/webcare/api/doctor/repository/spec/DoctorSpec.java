package com.pemits.webcare.api.doctor.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.doctor.entity.Doctor;

/**
 * @author Elvin Shrestha on 7/1/2020
 */
public class DoctorSpec implements Specification<Doctor> {

    private static final String FILTER_BY_NAME = "user.name";
    private static final String FILTER_BY_USER_ID = "user.id";
    public static final String FILTER_BY_DEPARTMENT_ID = "department.id";

    private final String property;
    private final String value;

    public DoctorSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_NAME:
                return criteriaBuilder.equal(root.join("user").get("name"), value.toLowerCase());
            case FILTER_BY_USER_ID:
                return criteriaBuilder.equal(root.join("user").get("id"), Long.valueOf(value));
            case FILTER_BY_DEPARTMENT_ID:
                return criteriaBuilder.equal(root.join("department").get("id"), Long.valueOf(value));
            default:
                return null;
        }
    }
}
