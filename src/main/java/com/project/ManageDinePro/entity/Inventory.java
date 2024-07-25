package com.project.ManageDinePro.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Inventory")
public class Inventory {

    @NotBlank(message = "Inventory food ID cannot be blank")
    private String inventory_food_id;

    @NotNull(message = "Inventory food quantity cannot be null")
    private int inventory_food_quantity;

    @NotNull(message = "Inventory threshold cannot be null")
    private int invotory_threshold;

    @NotNull(message = "Inventory last restock date cannot be null")
    private LocalDate inventory_last_restock_date;

    @NotBlank(message = "Inventory supplier info cannot be blank")
    private String inventory_supplier_info;

    public String getInventory_food_id() {
        return inventory_food_id;
    }

    public void setInventory_food_id(String inventory_food_id) {
        this.inventory_food_id = inventory_food_id;
    }

    public int getInventory_food_quantity() {
        return inventory_food_quantity;
    }

    public void setInventory_food_quantity(int inventory_food_quantity) {
        this.inventory_food_quantity = inventory_food_quantity;
    }

    public int getInvotory_threshold() {
        return invotory_threshold;
    }

    public void setInvotory_threshold(int invotory_threshold) {
        this.invotory_threshold = invotory_threshold;
    }

    public LocalDate getInventory_last_restock_date() {
        return inventory_last_restock_date;
    }

    public void setInventory_last_restock_date(LocalDate inventory_last_restock_date) {
        this.inventory_last_restock_date = inventory_last_restock_date;
    }

    public String getInventory_supplier_info() {
        return inventory_supplier_info;
    }

    public void setInventory_supplier_info(String inventory_supplier_info) {
        this.inventory_supplier_info = inventory_supplier_info;
    }

    public Inventory(@NotBlank(message = "Inventory food ID cannot be blank") String inventory_food_id,
            @NotNull(message = "Inventory food quantity cannot be null") int inventory_food_quantity,
            @NotNull(message = "Inventory threshold cannot be null") int invotory_threshold,
            @NotNull(message = "Inventory last restock date cannot be null") LocalDate inventory_last_restock_date,
            @NotBlank(message = "Inventory supplier info cannot be blank") String inventory_supplier_info) {
        this.inventory_food_id = inventory_food_id;
        this.inventory_food_quantity = inventory_food_quantity;
        this.invotory_threshold = invotory_threshold;
        this.inventory_last_restock_date = inventory_last_restock_date;
        this.inventory_supplier_info = inventory_supplier_info;
    }

}
