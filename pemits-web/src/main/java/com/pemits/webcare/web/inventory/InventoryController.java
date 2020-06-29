package com.pemits.webcare.web.inventory;

import com.pemits.webcare.api.inventory.entity.Inventory;
import com.pemits.webcare.api.inventory.service.InventoryService;
import com.pemits.webcare.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pemits.webcare.web.inventory.InventoryController.URL;

/**
 * @Author Mohammad Hussain
 * created on 6/29/2020
 */
@RestController
@RequestMapping(URL)
@Slf4j
public class InventoryController extends BaseController<Inventory, Long> {
    static final String URL = "/v1/inventory";

    public InventoryController(InventoryService inventoryService) {
        super(inventoryService, log.getClass());
    }
}
