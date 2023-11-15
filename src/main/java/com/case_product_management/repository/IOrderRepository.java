package com.case_product_management.repository;

import com.case_product_management.model.Order;
import com.case_product_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {
    public List<Order> findByUser(User user);
    public List<Object> takeOrderByMonthAndYear();
    public int countByOrderStatus(String orderStatus);
}
