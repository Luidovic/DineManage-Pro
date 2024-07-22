package com.project.ManageDinePro.dataAccessObject;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import com.project.ManageDinePro.entity.Customer;

@Service
public class CustomerService {

    private CustomerRepo customerRepo;
    private final MongoTemplate mongoTemplate;

    public CustomerService(CustomerRepo customerRepo, MongoTemplate mongoTemplate) {
        this.customerRepo = customerRepo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Customer> getAllEntities() {
        return customerRepo.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public boolean customerExists(String customer_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("customer_id").is(customer_id));
        return mongoTemplate.exists(query, (Class<?>) Customer.class);
    }

}
