package com.pemits.webcare.api.inventory.repository;

import com.pemits.webcare.api.inventory.entity.Inventory;
import com.pemits.webcare.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Mohammad Hussain
 * created on 6/29/2020
 */
@Repository
public interface InventoryRepository extends BaseRepository<Inventory, Long> {
}
