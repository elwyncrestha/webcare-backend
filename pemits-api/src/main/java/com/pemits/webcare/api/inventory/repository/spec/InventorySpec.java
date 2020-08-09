package com.pemits.webcare.api.inventory.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pemits.webcare.api.inventory.entity.Inventory;

/**
 * @Author Mohammad Hussain
 * created on 6/29/2020
 */
public class InventorySpec implements Specification<Inventory> {

    private static final String FILTER_BY_NAME = "name";

    private final String property;
    private final String value;

    public InventorySpec(String property, String value) {
        this.property = property;
        this.value = value;
    }


    @Override
    public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_NAME:
                return criteriaBuilder.like(root.get(FILTER_BY_NAME), "%" + value + "%");
            default:
                return null;
        }
    }
}
