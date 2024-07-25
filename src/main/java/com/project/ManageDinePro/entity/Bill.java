package com.project.ManageDinePro.entity;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bill")
public class Bill {

    @Id
    private String bill_id;

    @NotBlank(message = "payment method is mandatory")
    private String payment_method;

    @NotBlank(message = "price is mandatory")
    private double payment_price;

    @NotBlank(message = "status is mandatory")
    private String payment_status;

    public Bill(String bill_id, @NotBlank(message = "payment method is mandatory") String payment_method,
            @NotBlank(message = "price is mandatory") double payment_price,
            @NotBlank(message = "status is mandatory") String payment_status) {
        this.bill_id = bill_id;
        this.payment_method = payment_method;
        this.payment_price = payment_price;
        this.payment_status = payment_status;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public double getPayment_price() {
        return payment_price;
    }

    public void setPayment_price(double payment_price) {
        this.payment_price = payment_price;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

}
