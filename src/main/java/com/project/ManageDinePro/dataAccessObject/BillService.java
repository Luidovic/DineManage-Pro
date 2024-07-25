package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.project.ManageDinePro.entity.Bill;

@Service
public class BillService {
    
    private BillRepo billRepo;
    private final MongoTemplate mongoTemplate;

    public BillService(BillRepo billRepo, MongoTemplate mongoTemplate) {
        this.billRepo = billRepo;
        this.mongoTemplate = mongoTemplate;
    }


}
