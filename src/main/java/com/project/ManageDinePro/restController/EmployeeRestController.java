package com.project.ManageDinePro.restController;

import java.time.LocalDateTime;
import java.time.LocalDate; // Add this line to import LocalDate
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ManageDinePro.dataAccessObject.EmployeeService;
import com.project.ManageDinePro.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllemployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<ObjectNode> addEmployee(@RequestBody JsonNode req) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        // Check for missing fields
        if (req.get("employee_id") == null || req.get("employee_username") == null || req.get("employee_name") == null
                || req.get("employee_lastname") == null || req.get("employee_dateofbirth") == null
                || req.get("employee_email") == null || req.get("employee_gender") == null ||
                req.get("employee_position") == null || req.get("employee_salary") == null ||
                req.get("employee_startshift") == null || req.get("employee_endshift") == null) {

            objectNode.put("Message", "Missing fields in the request");
            return ResponseEntity.badRequest().body(objectNode);
        }

        String employee_id = req.get("employee_id").asText();
        String employee_username = req.get("employee_username").asText();
        String employee_name = req.get("employee_name").asText();
        String employee_lastname = req.get("employee_lastname").asText();
        LocalDate employee_dateOfBirth = LocalDate.parse(req.get("employee_dateofbirth").asText());
        String employee_email = req.get("employee_email").asText();
        String employee_gender = req.get("employee_gender").asText();
        String employee_position = req.get("employee_position").asText();
        String employee_startshift = req.get("employee_startshift").asText();
        String employee_endshift = req.get("employee_endshift").asText();
        double employee_salary;
        // Validate salary
        JsonNode salaryNode = req.get("employee_salary");
        if (!salaryNode.isNumber() || salaryNode.asDouble() < 0) {
            objectNode.put("Message", "Salary must be a positive number");
            return ResponseEntity.badRequest().body(objectNode);
        } else {
            employee_salary = salaryNode.asDouble();
        }

        // Check if employee already exists
        if (employeeService.getEmployeeById(employee_id) != null) {
            objectNode.put("Message", "Employee with ID " + employee_id + " already exists");
            return ResponseEntity.badRequest().body(objectNode);
        }

        // Check if salary is negative
        if (employee_salary < 0) {
            objectNode.put("Message", "Salary cannot be a negative number");
            return ResponseEntity.badRequest().body(objectNode);
        }

        if (!employee_email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            objectNode.put("Message", "Invalid email format");
            return ResponseEntity.badRequest().body(objectNode);
        }

        // Save the employee
        Employee employee = new Employee(employee_id, employee_username, employee_name, employee_lastname,
                employee_email, employee_gender, employee_dateOfBirth, employee_salary, employee_position,
                employee_startshift, employee_endshift);

        employeeService.addEmployee(employee);

        objectNode.put("Message", "Employee added successfully");
        return ResponseEntity.ok().body(objectNode);
    }

}
