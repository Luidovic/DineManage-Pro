package com.project.ManageDinePro.dataAccessObject;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ManageDinePro.entity.Order;

@Service
public class OrderService {

    private OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public void updateOrder(Order order) {
        Order existingOrder = orderRepo.findById(order.getOrder_id()).orElse(null);
        if (existingOrder != null) {
            existingOrder.setOrder_status("done");
            orderRepo.save(existingOrder);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}
