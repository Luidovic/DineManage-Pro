package com.project.ManageDinePro.dataAccessObject;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.ManageDinePro.entity.Employee;

public interface EmployeeRepo extends MongoRepository<Employee, String> {
    
}
