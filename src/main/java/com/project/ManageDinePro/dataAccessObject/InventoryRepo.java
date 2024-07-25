package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Inventory;

public interface InventoryRepo extends MongoRepository<Inventory, String> {

}
