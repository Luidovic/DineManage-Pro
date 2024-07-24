package com.project.ManageDinePro.dataAccessObject;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.project.ManageDinePro.entity.Food;
import com.project.ManageDinePro.entity.Menu;

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

}
