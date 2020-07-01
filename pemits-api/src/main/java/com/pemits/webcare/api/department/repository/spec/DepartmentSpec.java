package com.pemits.webcare.api.department.repository.spec;

import com.pemits.webcare.api.department.entity.Department;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author Mohammad Hussain
 * created on 6/22/2020
 */
public class DepartmentSpec implements Specification<Department> {

    private static final String FILTER_BY_NAME = "name";

    private final String property;
    private final String value;

    public DepartmentSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_NAME:
                return criteriaBuilder.like(root.get(FILTER_BY_NAME), "%" + value + "%");
        }
        return null;
    }
}
