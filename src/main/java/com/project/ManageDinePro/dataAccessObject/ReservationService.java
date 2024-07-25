package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRepo reservationRepo;
    private final MongoTemplate mongoTemplate;

    public ReservationService(ReservationRepo reservationRepo, MongoTemplate mongoTemplate) {
        this.reservationRepo = reservationRepo;
        this.mongoTemplate = mongoTemplate;
    }
}
