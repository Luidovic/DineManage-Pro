package com.project.ManageDinePro.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ManageDinePro.dataAccessObject.InventoryService;

@RestController
@RequestMapping("/api")
public class InventoryRestController {

    private InventoryService inventoryService;

    public InventoryRestController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

}
