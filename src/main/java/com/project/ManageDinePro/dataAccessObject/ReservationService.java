package com.project.ManageDinePro.dataAccessObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ManageDinePro.entity.Customer;
import com.project.ManageDinePro.entity.Reservation;

@Service
public class ReservationService {

    private ReservationRepo reservationRepo;
    private final MongoTemplate mongoTemplate;
    private CustomerService customerService;


    private final int MAX_RESERVATIONS = 50;
    private final Map<String, Integer> reservationCount = new HashMap<>();

    public ReservationService(ReservationRepo reservationRepo, MongoTemplate mongoTemplate,
            CustomerService customerService) {
        this.reservationRepo = reservationRepo;
        this.mongoTemplate = mongoTemplate;
        this.customerService = customerService;
    }

    public void createReservation(Reservation reservation) {
        // Set the reservation note to "pending"
        reservation.setReservation_note("pending");
        reservationRepo.save(reservation);

        // Simulate reservation processing time (20 seconds)
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Update the reservation note to "done"
                reservation.setReservation_note("done");
                reservationRepo.save(reservation);

            }
        }, 20000);
    }

    public int getAvailableReservations() {
        return (int) (MAX_RESERVATIONS - reservationRepo.count());
    }

    public boolean customerExists(String customerId) {
        return customerService.customerExists(customerId);
    }

}
