package com.pemits.webcare.api.helpdesk.repository.spec;

import com.pemits.webcare.api.helpdesk.entity.HelpDesk;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author Mohammad Hussain
 * created on 7/14/2020
 */
public class HelpDeskSpec implements Specification<HelpDesk> {

    private static final String FILTER_BY_NAME = "name";
    private static final String FILTER_BY_EMAIL = "email";

    private final String property;
    private final String value;

    public HelpDeskSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<HelpDesk> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_NAME:
                return criteriaBuilder.like(root.get(FILTER_BY_NAME), "%"+value+"%");
            case FILTER_BY_EMAIL:
                return criteriaBuilder.like(root.get(FILTER_BY_EMAIL), "$"+value+"%");
        }
        return null;
    }
}
