package com.pemits.webcare.api.inventory.repository.spec;

import com.pemits.webcare.api.inventory.entity.Inventory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Author Mohammad Hussain
 * created on 6/29/2020
 */
public class InventorySpec implements Specification<Inventory> {

    private final String property;
    private final String value;

    public InventorySpec(String property, String value) {
        this.property = property;
        this.value = value;
    }


    @Override
    public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
