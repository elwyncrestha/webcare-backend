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

    private final String property;
    private final String value;

    public DepartmentSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
