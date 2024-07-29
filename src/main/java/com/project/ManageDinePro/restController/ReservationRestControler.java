package com.project.ManageDinePro.restController;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ManageDinePro.dataAccessObject.ReservationService;
import com.project.ManageDinePro.entity.Reservation;

@RestController
@RequestMapping("/api")
public class ReservationRestControler {

    private ReservationService reservationService;

    public ReservationRestControler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public ResponseEntity<ObjectNode> createReservation(@RequestBody JsonNode req) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        // Check for missing fields
        if (req.get("reservation_id") == null || req.get("reservation_date") == null || req.get("reservation_time") == null ||
            req.get("reservation_customer_id") == null || req.get("reservation_numberofpeople") == null) {

            objectNode.put("Message", "Missing fields in the request");
            return ResponseEntity.badRequest().body(objectNode);
        }

        String reservation_id = req.get("reservation_id").asText();
        LocalDate reservation_date = LocalDate.parse(req.get("reservation_date").asText());
        String reservation_time = req.get("reservation_time").asText();
        String reservation_customer_id = req.get("reservation_customer_id").asText();
        double reservation_numberofpeople = req.get("reservation_numberofpeople").asDouble();

        // Check if the customer exists
        if (!reservationService.customerExists(reservation_customer_id)) {
            objectNode.put("Message", "Customer does not exist");
            return ResponseEntity.badRequest().body(objectNode);
        }

        // Check if there are enough available reservations
        int availableReservations = reservationService.getAvailableReservations();
        if (reservation_numberofpeople > availableReservations) {
            objectNode.put("Message", "Not enough available reservations");
            return ResponseEntity.badRequest().body(objectNode);
        }

        // Save the reservation with status "pending"
        Reservation reservation = new Reservation();
        reservation.setReservation_id(reservation_id);
        reservation.setReservation_date(reservation_date);
        reservation.setReservation_time(reservation_time);
        reservation.setReservation_customer_id(reservation_customer_id);
        reservation.setReservation_numberofpeople(reservation_numberofpeople);
        reservation.setReservation_note("pending"); // Initial status

        reservationService.createReservation(reservation);

        objectNode.put("Message", "Reservation is being processed. It will be finalized in 20 seconds.");
        return ResponseEntity.ok().body(objectNode);
    }

}
