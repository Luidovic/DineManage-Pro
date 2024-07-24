package com.project.ManageDinePro.restController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.ManageDinePro.dataAccessObject.CustomerService;
import com.project.ManageDinePro.entity.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getallcustomers")
    public List<Customer> getAllEntities() {
        return customerService.getAllEntities();
    }

    @PutMapping("/addcustomer")
    public ResponseEntity<ObjectNode> addCustomer(@RequestBody JsonNode req) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        // Check for missing fields
        if (req.get("customer_id") == null || req.get("customer_username") == null || req.get("customer_name") == null
                || req.get("customer_lastname") == null || req.get("customer_dateOfBirth") == null
                || req.get("customer_gender") == null || req.get("customer_phonenumber") == null
                || req.get("customer_email") == null || req.get("customer_reservationid") == null
                || req.get("customer_orderid") == null || req.get("customer_billid") == null
                || req.get("customer_preference") == null) {

            objectNode.put("message", "Missing fields in the request");
            return ResponseEntity.badRequest().body(objectNode);
        }

        String customer_id = req.get("customer_id").asText();
        String customer_username = req.get("customer_username").asText();
        String customer_name = req.get("customer_name").asText();
        String customer_lastname = req.get("customer_lastname").asText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate customer_dateOfBirth;
        try {
            customer_dateOfBirth = LocalDate.parse(req.get("customer_dateOfBirth").asText(), formatter);
        } catch (DateTimeParseException e) {
            objectNode.put("message", "Invalid date format");
            return ResponseEntity.badRequest().body(objectNode);
        }

        String customer_gender = req.get("customer_gender").asText();
        String customer_phonenumber = req.get("customer_phonenumber").asText();
        String customer_email = req.get("customer_email").asText();
        String customer_reservationid = req.get("customer_reservationid").asText();
        String customer_orderid = req.get("customer_orderid").asText();
        String customer_billid = req.get("customer_billid").asText();

        JsonNode preferenceNode = req.get("customer_preference");
        List<String> customer_preference = new ArrayList<>();

        if (preferenceNode.isArray()) {
            for (JsonNode pref : preferenceNode) {
                customer_preference.add(pref.asText());
            }
        } else {
            objectNode.put("message", "Preference must be a list of strings");
            return ResponseEntity.badRequest().body(objectNode);
        }

        // Check if customer already exists
        if (customerService.customerExists(customer_id)) {
            objectNode.put("message", "Customer already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(objectNode);
        }

        Customer customer = new Customer(customer_id, customer_username, customer_name, customer_lastname,
                customer_dateOfBirth, customer_gender, customer_phonenumber, customer_email,
                customer_reservationid, customer_orderid, customer_billid, customer_preference);

        customerService.saveCustomer(customer);

        objectNode.put("message", "Customer added successfully");
        return ResponseEntity.ok(objectNode);
    }

}
