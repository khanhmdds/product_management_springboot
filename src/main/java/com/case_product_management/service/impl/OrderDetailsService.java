package com.case_product_management.service.impl;

import com.case_product_management.model.OrderDetails;
import com.case_product_management.repository.IOrderDetailsRepository;
import com.case_product_management.service.IOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService implements IOrderDetailsService {
    @Autowired
    private IOrderDetailsRepository iOrderDetailsRepository;
    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {
        iOrderDetailsRepository.save(orderDetails);
    }
}
