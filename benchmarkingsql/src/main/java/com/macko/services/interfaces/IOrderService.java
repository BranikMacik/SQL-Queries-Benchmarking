package com.macko.services.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.macko.models.Order;

public interface IOrderService {
    
    List<Order> getAllOrders();

    Order getOrderById(long id);

    List<Order> getOrdersByCustomer(long customerId);

    List<Order> getOrdersByDate(LocalDate dateOfIssue);

    List<Order> getOrdersByProduct(long productId);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrderById(long orderId);
}
