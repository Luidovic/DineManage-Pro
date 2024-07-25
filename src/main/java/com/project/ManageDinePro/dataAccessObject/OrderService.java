package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepo orderRepo;
    private MongoTemplate mongoTemplate;

    public OrderService(OrderRepo orderRepo, MongoTemplate mongoTemplate) {
        this.orderRepo = orderRepo;
        this.mongoTemplate = mongoTemplate;
    }
}
