package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Bill;

public interface BillRepo extends MongoRepository<Bill, String> {
    
}
