package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private InventoryRepo inventoryRepo;
    private MongoTemplate mongoTemplate;

    public InventoryService(InventoryRepo inventoryRepo, MongoTemplate mongoTemplate) {
        this.inventoryRepo = inventoryRepo;
        this.mongoTemplate = mongoTemplate;
    }

}
