package com.pemits.webcare.api.user.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.user.entity.User;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
public class UserSpec implements Specification<User> {

    private final String property;
    private final String value;

    public UserSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
