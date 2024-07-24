package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Menu;

public interface MenuRepo extends MongoRepository<Menu, String> {

}
