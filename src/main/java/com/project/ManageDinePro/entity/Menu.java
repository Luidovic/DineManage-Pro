package com.project.ManageDinePro.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Menu")
public class Menu {

    private String food_id;
    private double price;
    private String food_category;
    private String food_name;
    private String food_url;
    private String food_description;

    public Menu(String food_id, double price, String food_category, String food_name, String food_url,
            String food_description) {
        this.food_id = food_id;
        this.price = price;
        this.food_category = food_category;
        this.food_name = food_name;
        this.food_url = food_url;
        this.food_description = food_description;
    }
    public Menu() {
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
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

}
