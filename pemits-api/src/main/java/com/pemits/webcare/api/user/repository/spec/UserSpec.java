package com.pemits.webcare.api.user.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.user.entity.User;
import com.pemits.webcare.core.enums.UserType;

/**
 * @author Elvin Shrestha on 6/21/2020
 */
public class UserSpec implements Specification<User> {

    private static final String FILTER_BY_NAME = "name";
    private static final String FILTER_BY_USER_TYPE = "userType";
    public static final String FILTER_BY_EMAIL = "email";

    private final String property;
    private final String value;

    public UserSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_NAME:
                return criteriaBuilder.like(root.get(FILTER_BY_NAME), "%" + value + "%");
            case FILTER_BY_USER_TYPE:
                return criteriaBuilder.equal(root.get(FILTER_BY_USER_TYPE), UserType.valueOf(value));
            case FILTER_BY_EMAIL:
                return criteriaBuilder.equal(root.get(FILTER_BY_EMAIL), value);
            default:
                return null;
        }
    }
}
