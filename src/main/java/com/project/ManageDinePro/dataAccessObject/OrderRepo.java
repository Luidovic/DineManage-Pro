package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Order;

public interface OrderRepo extends MongoRepository<Order, String> {

}
