package com.case_product_management.service;

import com.case_product_management.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void saveOrder(Order order);

    List<Order> getAllOrders();

    Optional<Order> getOrderById(long orderId);
}
