package com.pemits.webcare.api.inventory.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pemits.webcare.BaseJpaTest;
import com.pemits.webcare.api.inventory.entity.Inventory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class InventoryRepositoryTest extends BaseJpaTest {

    private static final String MOCK_INVENTORY_NAME1 = "Inventory1";
    private static final String MOCK_INVENTORY_NAME2 = "Inventory2";
    private static final long MOCK_INVENTORY_ID_1 = 1L;
    private static final long MOCK_INVENTORY_ID_2 = 2L;

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

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testSaveInventoryShouldUpdateInventory() {
        final Optional<Inventory> inventory = repository.findById(MOCK_INVENTORY_ID_1);

        assertThat(inventory.isPresent(), equalTo(true));
        inventory.get().setName(MOCK_INVENTORY_NAME1);
        inventory.get().setPrice(1200);
        inventory.get().setQuantity(2);
        inventory.get().setTotalPrice(1200 * 2);
        inventory.get().setPurchaseDate(LocalDate.now());
        Inventory update = repository.save(inventory.get());

        assertThat(repository.getOne(MOCK_INVENTORY_ID_1), is(update));
    }

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testFindInventoryByIdShouldReturnInventory() {

        final Optional<Inventory> inventory = repository.findById(MOCK_INVENTORY_ID_2);
        assertThat(inventory.isPresent(), equalTo(true));

        assertThat(inventory.get().getName(), is(MOCK_INVENTORY_NAME2));
    }

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testFindAllShouldReturnNotEmptyList() {
        final List<Inventory> inventories = repository.findAll();
        assertThat(inventories.size(), greaterThan(0));
    }

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testGetOneShouldReturnInventory() {
        final Inventory inventory = repository.getOne(MOCK_INVENTORY_ID_1);
        assertThat(inventory.getName(), is(MOCK_INVENTORY_NAME1));
    }

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testDeleteByIdShouldDeleteInventory() {
        long count = repository.count();
        repository.deleteById(MOCK_INVENTORY_ID_1);
        assertThat(repository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testDeleteShouldDeleteInventory() {
        long count = repository.count();
        final Inventory inventory = repository.getOne(MOCK_INVENTORY_ID_2);
        repository.delete(inventory);
        assertThat(repository.findAll(), hasSize((int) count - 1));
    }

    @Test
    @DatabaseSetup("/dataset/inventory/inventory-config.xml")
    public void testDeleteAllShouldReturnCountZero() {
        repository.deleteAll();
        assertThat(repository.findAll(), hasSize(0));
    }

}
