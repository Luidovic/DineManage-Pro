package com.project.ManageDinePro.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

import javax.validation.constraints.NotBlank;

@Document(collection = "Food")
public class Food {

    @Id
    private String food_id;

    @NotBlank(message = "Food name is mandatory")
    @Indexed(unique = true)
    private String food_name;

    @NotBlank(message = "Food ingredients are mandatory")
    private List<String> food_ingredients;

    @NotBlank(message = "Food category is mandatory")
    private String food_category;

    @NotBlank(message = "Food URL is mandatory")
    private String food_url;

    @NotBlank(message = "Food description is mandatory")
    private String food_description;

    @NotBlank(message = "Food price is mandatory")
    private double food_price;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public List<String> getFood_ingredients() {
        return food_ingredients;
    }

    public void setFood_ingredients(List<String> food_ingredients) {
        this.food_ingredients = food_ingredients;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getFood_url() {
        return food_url;
    }

    public void setFood_url(String food_url) {
        this.food_url = food_url;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }

    public Food(String food_id, @NotBlank(message = "Food name is mandatory") String food_name,
            @NotBlank(message = "Food ingredients are mandatory") List<String> food_ingredients,
            @NotBlank(message = "Food category is mandatory") String food_category,
            @NotBlank(message = "Food URL is mandatory") String food_url,
            @NotBlank(message = "Food description is mandatory") String food_description,
            @NotBlank(message = "Food price is mandatory") double food_price) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_ingredients = food_ingredients;
        this.food_category = food_category;
        this.food_url = food_url;
        this.food_description = food_description;
        this.food_price = food_price;
    }

   

}
