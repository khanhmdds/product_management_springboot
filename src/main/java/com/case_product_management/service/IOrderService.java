package com.case_product_management.service;

import com.case_product_management.dto.SearchOrderObject;
import com.case_product_management.model.Order;
import com.case_product_management.model.User;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;

public interface IOrderService {
//    Page<Order> getAllOrderByFilter(SearchOrderObject object, int page) throws ParseException;
    Order update(Order order);
    Order findById(long id);
    Order save(Order order);

    List<Object> takeOrderByMonthAndYear();
    List<Order> getOrderByUser(User user);
    int countByOrderStatus(String orderStatus);

}
