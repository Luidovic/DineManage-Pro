package com.project.ManageDinePro.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ManageDinePro.dataAccessObject.FoodService;
import com.project.ManageDinePro.dataAccessObject.MenuService;
import com.project.ManageDinePro.entity.Food;
import com.project.ManageDinePro.entity.Menu;

@RestController
@RequestMapping("/api")
public class MenuRestController {

    public MenuService menuService;
    public FoodService foodService;

    public MenuRestController(MenuService menuService, FoodService foodService) {
        this.menuService = menuService;
        this.foodService = foodService;
    }

    @GetMapping("/viewmenu")
    public List<Menu> viewMenu() {
        return menuService.getAllEntities();
    }

    @PostMapping("/addFoodToMenu")
    public ResponseEntity<ObjectNode> addFoodToMenu(@RequestBody ObjectNode request) {
        String foodId = request.get("food_id").asText();

        // Check if the food ID is provided
        if (foodId == null || foodId.isEmpty()) {
            ObjectNode response = JsonNodeFactory.instance.objectNode();
            response.put("status", "error");
            response.put("message", "Food ID is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Retrieve the food item from the Food collection
        Food food = foodService.getFoodById(foodId);

        // Check if the food item exists
        if (food == null) {
            ObjectNode response = JsonNodeFactory.instance.objectNode();
            response.put("status", "error");
            response.put("message", "Food item does not exist in the Food collection");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        // Add the food item to the menu
        menuService.addFoodToMenu(food);

        ObjectNode response = JsonNodeFactory.instance.objectNode();
        response.put("status", "success");
        response.put("message", "Food added to menu successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
