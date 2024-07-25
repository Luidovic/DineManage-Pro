package com.project.ManageDinePro.dataAccessObject;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.project.ManageDinePro.entity.Employee;

@Service
public class EmployeeService {
    private EmployeeRepo employeeRepo;
    private final MongoTemplate mongoTemplate;

    public EmployeeService(EmployeeRepo employeeRepo, MongoTemplate mongoTemplate) {
        this.employeeRepo = employeeRepo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public boolean employeeExists(String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeId").is(employeeId));
        return mongoTemplate.exists(query, Employee.class);
    }

    public Employee getEmployeeById(String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employee_id").is(employeeId));
        return mongoTemplate.findOne(query, Employee.class);
    }
}
