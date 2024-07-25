package com.project.ManageDinePro.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;

import javax.validation.constraints.NotBlank;

@Document(collection = "Order")
public class Order {
    
    @Id
    private String order_id;
    @NotBlank(message = "Order items cannot be blank")
    private List<String> order_items;

    @NotBlank(message = "Order total price cannot be blank")
    private double order_totalprice;

    @NotBlank(message = "Order status cannot be blank")
    private String order_status;

    @NotBlank(message = "Order time cannot be blank")
    private String order_time;

    @NotBlank(message = "Order customer ID cannot be blank")
    private String order_customer_id;

    @NotBlank(message = "Order notes cannot be blank")
    private String order_notes;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public List<String> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<String> order_items) {
        this.order_items = order_items;
    }

    public double getOrder_totalprice() {
        return order_totalprice;
    }

    public void setOrder_totalprice(double order_totalprice) {
        this.order_totalprice = order_totalprice;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_customer_id() {
        return order_customer_id;
    }

    public void setOrder_customer_id(String order_customer_id) {
        this.order_customer_id = order_customer_id;
    }

    public String getOrder_notes() {
        return order_notes;
    }

    public void setOrder_notes(String order_notes) {
        this.order_notes = order_notes;
    }

    public Order(String order_id, @NotBlank(message = "Order items cannot be blank") List<String> order_items,
            @NotBlank(message = "Order total price cannot be blank") double order_totalprice,
            @NotBlank(message = "Order status cannot be blank") String order_status,
            @NotBlank(message = "Order time cannot be blank") String order_time,
            @NotBlank(message = "Order customer ID cannot be blank") String order_customer_id,
            @NotBlank(message = "Order notes cannot be blank") String order_notes) {
        this.order_id = order_id;
        this.order_items = order_items;
        this.order_totalprice = order_totalprice;
        this.order_status = order_status;
        this.order_time = order_time;
        this.order_customer_id = order_customer_id;
        this.order_notes = order_notes;
    }

}
