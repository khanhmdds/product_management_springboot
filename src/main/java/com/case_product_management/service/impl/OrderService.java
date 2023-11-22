package com.case_product_management.service.impl;

import com.case_product_management.model.Order;
import com.case_product_management.repository.IOrderRepository;
import com.case_product_management.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;
    @Override
    public void saveOrder(Order order) {
        iOrderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return iOrderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(long orderId) {
        return iOrderRepository.findById(orderId);
    }
}
