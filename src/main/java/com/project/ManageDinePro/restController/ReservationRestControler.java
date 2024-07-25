package com.project.ManageDinePro.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ManageDinePro.dataAccessObject.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationRestControler {
    
    private ReservationService reservationService;

    public ReservationRestControler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
}
