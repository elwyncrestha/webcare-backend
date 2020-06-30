package com.pemits.webcare.api.inventory.service;

import com.pemits.webcare.api.inventory.entity.Inventory;
import com.pemits.webcare.api.inventory.repository.InventoryRepository;
import com.pemits.webcare.api.inventory.repository.spec.InventorySpecBuilder;
import com.pemits.webcare.core.repository.BaseSpecBuilder;
import com.pemits.webcare.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author Mohammad Hussain
 * created on 6/29/2020
 */
@Service
public class InventroyServiceImpl extends BaseServiceImpl<Inventory, Long>
        implements InventoryService {

    public InventroyServiceImpl(InventoryRepository inventoryRepository) {
        super(inventoryRepository);
    }

    @Override
    protected BaseSpecBuilder<Inventory> getSpec(Map<String, String> filterParams) {

        return new InventorySpecBuilder(filterParams);
    }
}
