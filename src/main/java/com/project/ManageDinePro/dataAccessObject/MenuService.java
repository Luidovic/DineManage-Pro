package com.project.ManageDinePro.dataAccessObject;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.project.ManageDinePro.entity.Food;
import com.project.ManageDinePro.entity.Menu;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class MenuService {

    private MenuRepo menuRepo;
    private MongoTemplate mongotemplate;

    public MenuService(MenuRepo menuRepo, MongoTemplate mongotemplate) {
        this.menuRepo = menuRepo;
        this.mongotemplate = mongotemplate;
    }

    public List<Menu> getAllEntities() {
        return menuRepo.findAll();
    }

    public void addFoodToMenu(Food food) {

        Menu menu = new Menu();
        menu.setFood_id(food.getFood_id());
        menu.setFood_name(food.getFood_name());
        menu.setFood_url(food.getFood_url());
        menu.setFood_category(food.getFood_category());
        menu.setFood_description(food.getFood_description());
        menu.setPrice(food.getFood_price());

        menuRepo.save(menu);
    }

    public boolean itemsExist(List<String> orderItems) {
        // Create a query to find Menu items where food_name is in the orderItems list
        Query query = new Query(Criteria.where("food_name").in(orderItems));

        // Find all Menu items matching the query
        List<Menu> menus = mongotemplate.find(query, Menu.class);

        // Extract the set of food names from the found Menu items
        Set<String> menuFoodNames = menus.stream()
                .map(Menu::getFood_name)
                .collect(Collectors.toSet());

        // Check if all order items are present in the menuFoodNames set
        return menuFoodNames.containsAll(orderItems);
    }

    public Double getItemPrice(String item) {
        // Create a query to find a Menu item by food_name
        Query query = new Query(Criteria.where("food_name").is(item));

        // Find the Menu item matching the query
        Menu menuItem = mongotemplate.findOne(query, Menu.class);

        // Return the price if found, or null if not found
        return menuItem != null ? menuItem.getPrice() : null;
    }

}
