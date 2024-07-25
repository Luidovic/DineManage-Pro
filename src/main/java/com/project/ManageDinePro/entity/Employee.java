package com.project.ManageDinePro.entity;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
public class Employee {

    @Id
    @NotBlank(message = "Employee ID cannot be blank")
    private String employee_id;

    @NotBlank(message = "Employee username cannot be blank")
    private String employee_username;

    @NotBlank(message = "Employee name cannot be blank")
    private String employee_name;

    @NotBlank(message = "Employee last name cannot be blank")
    private String employee_lastname;

    @NotBlank(message = "Employee email cannot be blank")
    @Email(message = "Email should be valid")
    private String employee_email;

    @NotBlank(message = "Employee gender cannot be blank")
    private String employee_gender;

    @NotBlank(message = "Employee date of birth cannot be blank")
    private LocalDate employee_dateofbirth;

    @NotBlank(message = "Employee phone number cannot be blank")
    private double employee_salary;

    @NotBlank(message = "Employee position cannot be blank")
    private String employee_position;

    @NotBlank(message = "Employee start shift date cannot be blank")
    private String employee_startshift;

    @NotBlank(message = "Employee end shift date cannot be blank")
    private String employee_endshift;

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_username() {
        return employee_username;
    }

    public void setEmployee_username(String employee_username) {
        this.employee_username = employee_username;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_lastname() {
        return employee_lastname;
    }

    public void setEmployee_lastname(String employee_lastname) {
        this.employee_lastname = employee_lastname;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_gender() {
        return employee_gender;
    }

    public void setEmployee_gender(String employee_gender) {
        this.employee_gender = employee_gender;
    }

    public LocalDate getEmployee_dateofbirth() {
        return employee_dateofbirth;
    }

    public void setEmployee_dateofbirth(LocalDate employee_dateofbirth) {
        this.employee_dateofbirth = employee_dateofbirth;
    }

    public double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_position() {
        return employee_position;
    }

    public void setEmployee_position(String employee_position) {
        this.employee_position = employee_position;
    }

    public String getEmployee_startshift() {
        return employee_startshift;
    }

    public void setEmployee_startshift(String employee_startshift) {
        this.employee_startshift = employee_startshift;
    }

    public String getEmployee_endshift() {
        return employee_endshift;
    }

    public void setEmployee_endshift(String employee_endshift) {
        this.employee_endshift = employee_endshift;
    }

    public Employee(@NotBlank(message = "Employee ID cannot be blank") String employee_id,
            @NotBlank(message = "Employee username cannot be blank") String employee_username,
            @NotBlank(message = "Employee name cannot be blank") String employee_name,
            @NotBlank(message = "Employee last name cannot be blank") String employee_lastname,
            @NotBlank(message = "Employee email cannot be blank") String employee_email,
            @NotBlank(message = "Employee gender cannot be blank") String employee_gender,
            @NotBlank(message = "Employee date of birth cannot be blank") LocalDate employee_dateofbirth,
            @NotBlank(message = "Employee phone number cannot be blank") double employee_salary,
            @NotBlank(message = "Employee position cannot be blank") String employee_position,
            @NotBlank(message = "Employee start shift date cannot be blank") String employee_startshift,
            @NotBlank(message = "Employee end shift date cannot be blank") String employee_endshift) {
        this.employee_id = employee_id;
        this.employee_username = employee_username;
        this.employee_name = employee_name;
        this.employee_lastname = employee_lastname;
        this.employee_email = employee_email;
        this.employee_gender = employee_gender;
        this.employee_dateofbirth = employee_dateofbirth;
        this.employee_salary = employee_salary;
        this.employee_position = employee_position;
        this.employee_startshift = employee_startshift;
        this.employee_endshift = employee_endshift;
    }

}
