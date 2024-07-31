package com.project.ManageDinePro.restController;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/addcustomer")
    public ResponseEntity<ObjectNode> addCustomer(
            @RequestParam("customer_id") String customer_id,
            @RequestParam("customer_username") String customer_username,
            @RequestParam("customer_name") String customer_name,
            @RequestParam("customer_lastname") String customer_lastname,
            @RequestParam("customer_dateOfBirth") String customer_dateOfBirth,
            @RequestParam("customer_gender") String customer_gender,
            @RequestParam("customer_phonenumber") String customer_phonenumber,
            @RequestParam("customer_email") String customer_email,
            @RequestParam("customer_image") MultipartFile customer_image) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        // Check for missing fields
        if (customer_id == null || customer_username == null || customer_name == null
                || customer_lastname == null || customer_dateOfBirth == null
                || customer_gender == null || customer_phonenumber == null
                || customer_email == null || customer_image == null) {

            objectNode.put("message", "Missing fields in the request");
            return ResponseEntity.badRequest().body(objectNode);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate customer_dateOfBirthParsed;
        try {
            customer_dateOfBirthParsed = LocalDate.parse(customer_dateOfBirth, formatter);
        } catch (DateTimeParseException e) {
            objectNode.put("message", "Invalid date format");
            return ResponseEntity.badRequest().body(objectNode);
        }

        // Convert image to byte array
        byte[] customer_imageBytes = customer_image.getBytes();

        // Check if customer already exists
        if (customerService.customerExists(customer_id)) {
            objectNode.put("message", "Customer already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(objectNode);
        }

        Customer customer = new Customer(customer_id, customer_username, customer_name, customer_lastname,
                customer_dateOfBirthParsed, customer_gender, customer_phonenumber, customer_email,
                customer_imageBytes);

        customerService.saveCustomer(customer);

        objectNode.put("message", "Customer added successfully");
        return ResponseEntity.ok(objectNode);
    }

}
