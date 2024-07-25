package com.project.ManageDinePro.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ManageDinePro.dataAccessObject.OrderService;
import com.project.ManageDinePro.entity.Order;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    private OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

}
