package com.pemits.webcare.api.inventory.repository.spec;

import com.pemits.webcare.api.inventory.entity.Inventory;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 6/29/2020
 */
public class InventorySpecBuilder extends BaseSpecBuilder<Inventory> {

    public InventorySpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Inventory> getSpecification(String property, String filterValue) {
        return null;
    }
}
