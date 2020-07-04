package com.pemits.webcare.api.inventory.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.inventory.entity.Inventory;

public class InventoryRepositoryTest extends BaseJpaTest {

    private static final String MOCK_INVENTORY_NAME1 = "Inventory1";
    private static final String MOCK_INVENTORY_NAME2 = "Inventory2";

    @Autowired
    private InventoryRepository repository;

    @Test
    public void testSaveShouldReturnSavedInventory() {
        Inventory inventory = new Inventory();
        inventory.setName(MOCK_INVENTORY_NAME1);
        inventory.setPrice(1200.50f);
        inventory.setQuantity(100);
        inventory.setTotalPrice(1200.50f * 100);
        inventory.setPurchaseDate(LocalDate.now());

        Inventory saved = repository.save(inventory);

        assertThat(saved.getId(), notNullValue());
    }

    @Test
    public void testSaveAllShouldReturnAllSavedInventories() {
        Inventory inventory1 = new Inventory();
        inventory1.setName(MOCK_INVENTORY_NAME1);
        inventory1.setPrice(1200.50f);
        inventory1.setQuantity(100);
        inventory1.setTotalPrice(1200.50f * 100);
        inventory1.setPurchaseDate(LocalDate.now());

        Inventory inventory2 = new Inventory();
        inventory2.setName(MOCK_INVENTORY_NAME2);
        inventory2.setPrice(2200.50f);
        inventory2.setQuantity(100);
        inventory2.setTotalPrice(2200.50f * 100);
        inventory2.setPurchaseDate(LocalDate.now());

        List<Inventory> saved = repository.saveAll(Arrays.asList(inventory1, inventory2));

        assertThat(saved, hasSize(2));
        assertThat(saved.get(0).getId(), notNullValue());
        assertThat(saved.get(1).getId(), notNullValue());
    }

}
