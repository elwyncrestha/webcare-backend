package com.pemits.webcare.api.feedback.repository.spec;

import com.pemits.webcare.api.feedback.entity.Feedback;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author Mohammad Hussain
 * created on 7/30/2020
 */
public class FeedbackSpec implements Specification<Feedback> {

    static final String FILTER_BY_FULL_NAME = "fullName";

    private final String property;
    private final String value;

    public FeedbackSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_FULL_NAME:
                return criteriaBuilder.like(root.get(FILTER_BY_FULL_NAME), "%" + value + "%");
        }
        return null;
    }
}
