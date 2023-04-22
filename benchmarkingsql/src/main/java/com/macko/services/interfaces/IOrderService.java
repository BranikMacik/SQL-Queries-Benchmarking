package com.macko.services.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.macko.models.Order;

public interface IOrderService {
    
    List<Order> getAllOrders();

    Order getOrderById(UUID id);

    List<Order> getOrdersByCustomer(UUID customerId);

    List<Order> getOrdersByDate(LocalDate dateOfIssue);

    List<Order> getOrdersByProduct(UUID productId);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrderById(UUID orderId);
}
