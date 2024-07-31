package com.project.ManageDinePro.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.util.*;

@Document(collection = "Customer")
public class Customer {

    @Id
    private String customer_id;

    @NotBlank(message = "Username is mandatory")
    private String customer_username;

    @NotBlank(message = "First Name is mandatory")
    private String customer_name;

    @NotBlank(message = "Last Name is mandatory")
    private String customer_lastname;

    @NotNull(message = "Date of birth is mandatory")
    private LocalDate customer_dateOfBirth;

    @NotBlank(message = "Gender is mandatory")
    private String customer_gender;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Invalid phone number")
    private String customer_phonenumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String customer_email;

    private String customer_reservationid;

    private String customer_orderid;

    private String customer_billid;

    private List<String> customer_preference;

    @Field(targetType = FieldType.BINARY)
    private byte[] customer_image;

    public Customer(String customer_id, @NotBlank(message = "Username is mandatory") String customer_username,
            @NotBlank(message = "First Name is mandatory") String customer_name,
            @NotBlank(message = "Last Name is mandatory") String customer_lastname,
            @NotNull(message = "Date of birth is mandatory") LocalDate customer_dateOfBirth,
            @NotBlank(message = "Gender is mandatory") String customer_gender,
            @NotBlank(message = "Phone number is mandatory") @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Invalid phone number") String customer_phonenumber,
            @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String customer_email,
            byte[] customer_image) {
        this.customer_id = customer_id;
        this.customer_username = customer_username;
        this.customer_name = customer_name;
        this.customer_lastname = customer_lastname;
        this.customer_dateOfBirth = customer_dateOfBirth;
        this.customer_gender = customer_gender;
        this.customer_phonenumber = customer_phonenumber;
        this.customer_email = customer_email;
        this.customer_image = customer_image;
    }

    public byte[] getCustomer_image() {
        return customer_image;
    }

    public void setCustomer_image(byte[] customer_image) {
        this.customer_image = customer_image;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_lastname() {
        return customer_lastname;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    public LocalDate getCustomer_dateOfBirth() {
        return customer_dateOfBirth;
    }

    public void setCustomer_dateOfBirth(LocalDate customer_dateOfBirth) {
        this.customer_dateOfBirth = customer_dateOfBirth;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public String getCustomer_phonenumber() {
        return customer_phonenumber;
    }

    public void setCustomer_phonenumber(String customer_phonenumber) {
        this.customer_phonenumber = customer_phonenumber;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_reservationid() {
        return customer_reservationid;
    }

    public void setCustomer_reservationid(String customer_reservationid) {
        this.customer_reservationid = customer_reservationid;
    }

    public String getCustomer_orderid() {
        return customer_orderid;
    }

    public void setCustomer_orderid(String customer_orderid) {
        this.customer_orderid = customer_orderid;
    }

    public String getCustomer_billid() {
        return customer_billid;
    }

    public void setCustomer_billid(String customer_billid) {
        this.customer_billid = customer_billid;
    }

    public List<String> getCustomer_preference() {
        return customer_preference;
    }

    public void setCustomer_preference(List<String> customer_preference) {
        this.customer_preference = customer_preference;
    }

}
