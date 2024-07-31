package com.project.ManageDinePro.restController;

import java.util.List;
import java.util.TimerTask;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.ManageDinePro.dataAccessObject.CustomerService;
import com.project.ManageDinePro.dataAccessObject.OrderService;
import com.project.ManageDinePro.dataAccessObject.MenuService;
import com.project.ManageDinePro.entity.Order;


import java.util.Timer;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    private OrderService orderService;
    private CustomerService customerService;
    private MenuService menuService;

    public OrderRestController(OrderService orderService, CustomerService customerService, MenuService menuService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.menuService = menuService;
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    // @PostMapping("/createOrder")
    // public ResponseEntity<ObjectNode> createOrder(@RequestBody JsonNode req) {
    // ObjectMapper objectMapper = new ObjectMapper();
    // ObjectNode objectNode = objectMapper.createObjectNode();

    // // Check for missing fields
    // if (req.get("order_items") == null || req.get("order_totalprice") == null ||
    // req.get("order_status") == null || req.get("order_time") == null ||
    // req.get("order_customer_id") == null || req.get("order_notes") == null) {

    // objectNode.put("Message", "Missing fields in the request");
    // return ResponseEntity.badRequest().body(objectNode);
    // }

    // if(customerService.customerExists(req.get("order_customer_id").asText()) ==
    // false) {
    // objectNode.put("Message", "Customer does not exist");
    // return ResponseEntity.badRequest().body(objectNode);
    // }

    // List<String> orderItems = objectMapper.convertValue(req.get("order_items"),
    // List.class);
    // double orderTotalPrice = req.get("order_totalprice").asDouble();
    // String orderStatus = req.get("order_status").asText();
    // String orderTime = req.get("order_time").asText();
    // String orderCustomerId = req.get("order_customer_id").asText();
    // String orderNotes = req.get("order_notes").asText();

    // // Create a new order
    // Order order = new Order();
    // order.setOrder_items(orderItems);
    // order.setOrder_totalprice(orderTotalPrice);
    // order.setOrder_status(orderStatus);
    // order.setOrder_time(orderTime);
    // order.setOrder_customer_id(orderCustomerId);
    // order.setOrder_notes(orderNotes);

    // orderService.createOrder(order);

    // objectNode.put("Message", "Order created successfully");
    // return ResponseEntity.ok().body(objectNode);
    // }

    @PostMapping("/createOrder")
    public ResponseEntity<ObjectNode> createOrder(@RequestBody JsonNode req) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        // Check for missing fields
        if (req.get("order_items") == null || req.get("order_status") == null ||
                req.get("order_time") == null || req.get("order_customer_id") == null ||
                req.get("order_notes") == null) {

            objectNode.put("Message", "Missing fields in the request");
            return ResponseEntity.badRequest().body(objectNode);
        }

        String customerId = req.get("order_customer_id").asText();
        if (!customerService.customerExists(customerId)) {
            objectNode.put("Message", "Customer does not exist");
            return ResponseEntity.badRequest().body(objectNode);
        }

        List<String> orderItems = objectMapper.convertValue(req.get("order_items"), List.class);
        if (!menuService.itemsExist(orderItems)) {
            objectNode.put("Message", "One or more items do not exist in the menu");
            return ResponseEntity.badRequest().body(objectNode);
        }

        double orderTotalPrice = calculateTotalPrice(orderItems); // Calculate total price
        String orderStatus = "pending"; // Initialize status as pending
        String orderTime = req.get("order_time").asText();
        String orderNotes = req.get("order_notes").asText();

        // Create a new order
        Order order = new Order();
        order.setOrder_items(orderItems);
        order.setOrder_totalprice(orderTotalPrice);
        order.setOrder_status(orderStatus);
        order.setOrder_time(orderTime);
        order.setOrder_customer_id(customerId);
        order.setOrder_notes(orderNotes);

        orderService.createOrder(order);

        // Schedule status update
        scheduleStatusUpdate(order);

        objectNode.put("Message", "Order created successfully");
        return ResponseEntity.ok().body(objectNode);
    }

    private double calculateTotalPrice(List<String> orderItems) {
        double totalPrice = 0.0;
        for (String item : orderItems) {
            Double price = menuService.getItemPrice(item);
            if (price != null) {
                totalPrice += price;
            }
        }
        return totalPrice;
    }

    private void scheduleStatusUpdate(Order order) {
        // Simulate status update after 20 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                order.setOrder_status("done");
                orderService.updateOrder(order); // Assuming this method updates an existing order
            }
        }, 20000); // 20 seconds delay
    }
}
