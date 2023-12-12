package com.case_product_management.model.response;

import com.case_product_management.model.Order;

public class OrderResponse {
    private Long orderId;
    private String orderStatus;
    private double totalPrice;

    public OrderResponse(Order order) {
        this.orderId = order.getOrder_id();
        this.orderStatus = order.getOrderStatus();
        this.totalPrice = order.getTotalPrice();
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
