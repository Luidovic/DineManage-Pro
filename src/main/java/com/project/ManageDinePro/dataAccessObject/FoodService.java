package com.project.ManageDinePro.dataAccessObject;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.project.ManageDinePro.entity.Food;

@Service
public class FoodService {

    private FoodRepo foodRepo;
    private final MongoTemplate mongoTemplate;

    public FoodService(FoodRepo foodRepo, MongoTemplate mongoTemplate, MenuService menuService) {
        this.foodRepo = foodRepo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Food> getAllEntities() {
        return foodRepo.findAll();
    }

    public void saveFood(Food food) {
        foodRepo.save(food);
    }

    public boolean foodExists(String foodName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("food_id").is(foodName));
        return mongoTemplate.exists(query, Food.class);
    }

    public Food getFoodById(String foodId) {
        return foodRepo.findById(foodId).orElse(null);
    }
}
