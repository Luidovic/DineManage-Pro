package com.project.ManageDinePro.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Reservation")
public class Reservation {
    
    @Id
    private String reservation_id;
    @NotBlank(message = "Reservation date is required")
    private LocalDate reservation_date;

    @NotBlank(message = "Reservation time is required")
    private String reservation_time;

    @NotBlank(message = "Customer ID is required")
    private String reservation_customer_id;

    @NotNull(message = "Number of people is required")
    private double reservation_numberofpeople;

    @NotBlank(message = "Reservation note is required")
    private String reservation_note;

    public Reservation(String reservation_id,
            @NotBlank(message = "Reservation date is required") LocalDate reservation_date,
            @NotBlank(message = "Reservation time is required") String reservation_time,
            @NotBlank(message = "Customer ID is required") String reservation_customer_id,
            @NotNull(message = "Number of people is required") double reservation_numberofpeople,
            @NotBlank(message = "Reservation note is required") String reservation_note) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.reservation_time = reservation_time;
        this.reservation_customer_id = reservation_customer_id;
        this.reservation_numberofpeople = reservation_numberofpeople;
        this.reservation_note = reservation_note;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }

    public String getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(String reservation_time) {
        this.reservation_time = reservation_time;
    }

    public String getReservation_customer_id() {
        return reservation_customer_id;
    }

    public void setReservation_customer_id(String reservation_customer_id) {
        this.reservation_customer_id = reservation_customer_id;
    }

    public double getReservation_numberofpeople() {
        return reservation_numberofpeople;
    }

    public void setReservation_numberofpeople(double reservation_numberofpeople) {
        this.reservation_numberofpeople = reservation_numberofpeople;
    }

    public String getReservation_note() {
        return reservation_note;
    }

    public void setReservation_note(String reservation_note) {
        this.reservation_note = reservation_note;
    }
    
}
