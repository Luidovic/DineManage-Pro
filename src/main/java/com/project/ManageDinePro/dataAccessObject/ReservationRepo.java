package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Reservation;

public interface ReservationRepo extends MongoRepository<Reservation, String> {

}
