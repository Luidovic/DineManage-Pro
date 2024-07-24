package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Food;

public interface FoodRepo extends MongoRepository<Food, String> {
    
}
