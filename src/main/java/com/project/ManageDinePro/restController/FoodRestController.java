package com.project.ManageDinePro.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // Add this import statement
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ManageDinePro.dataAccessObject.FoodService;
import com.project.ManageDinePro.entity.Food;

@RestController
@RequestMapping("/api")
public class FoodRestController {

    private FoodService foodService;

    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/getallfoods")
    public List<Food> getAllEntities() {
        return foodService.getAllEntities();
    }

    @PutMapping("/addfood")
    public ResponseEntity<ObjectNode> addFood(@RequestBody JsonNode req) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        if (!isValidRequest(req, objectNode)) {
            return ResponseEntity.badRequest().body(objectNode);
        }

        String food_id = req.get("food_id").asText();
        String food_name = req.get("food_name").asText();
        String food_category = req.get("food_category").asText();
        String food_url = req.get("food_url").asText();
        String food_description = req.get("food_description").asText();
        List<String> food_ingredients = objectMapper.convertValue(req.get("food_ingredients"), List.class);
        double food_price = req.get("food_price").asDouble(); // Add this line to get the food price

        if (foodService.foodExists(food_id)) {
            objectNode.put("message", "Food already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(objectNode);
        }

        Food food = new Food(food_id, food_name, food_ingredients, food_category, food_url, food_description, food_price); // Add the food price to the constructor

        foodService.saveFood(food);
        objectNode.put("message", "Food added successfully");
        return ResponseEntity.ok(objectNode);
    }

    private boolean isValidRequest(JsonNode req, ObjectNode objectNode) {
        if (req.get("food_id") == null || req.get("food_name") == null || req.get("food_ingredients") == null
                || req.get("food_url") == null || req.get("food_description") == null
                || req.get("food_category") == null || req.get("food_price") == null) { // Add the food price check
            objectNode.put("message", "Missing fields in the request");
            return false;
        }

        if (req.get("food_id").asText().isEmpty()) {
            objectNode.put("message", "food_id cannot be empty");
            return false;
        }

        if (req.get("food_name").asText().isEmpty()) {
            objectNode.put("message", "food_name cannot be empty");
            return false;
        }

        if (!req.get("food_ingredients").isArray() || req.get("food_ingredients").size() == 0) {
            objectNode.put("message", "food_ingredients must be a non-empty array");
            return false;
        }

        if (req.get("food_url").asText().isEmpty()) {
            objectNode.put("message", "food_url cannot be empty");
            return false;
        }

        if (req.get("food_description").asText().isEmpty()) {
            objectNode.put("message", "food_description cannot be empty");
            return false;
        }

        if (req.get("food_category").asText().isEmpty()) {
            objectNode.put("message", "food_category cannot be empty");
            return false;
        }

        if (req.get("food_price").asDouble() <= 0) { // Add the food price check
            objectNode.put("message", "food_price must be greater than 0");
            return false;
        }

        return true;
    }

}
