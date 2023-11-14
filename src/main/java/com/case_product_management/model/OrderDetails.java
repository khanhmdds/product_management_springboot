package com.case_product_management.model;

import javax.persistence.*;

@Entity
@Table(name = "orderDetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderDetail_id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private long unitPrice;

    private int quantityOrder;

//    private int soLuongNhanHang;

    @ManyToOne
    @JoinColumn(name = "order_id")
//    @JsonIgnore
    private Order order;

    public OrderDetails() {
    }

    public OrderDetails(long orderDetail_id, Product product, long unitPrice, int quantityOrder, Order order) {
        this.orderDetail_id = orderDetail_id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantityOrder = quantityOrder;
        this.order = order;
    }

    public long getOrderDetail_id() {
        return orderDetail_id;
    }

    public void setOrderDetail_id(long orderDetail_id) {
        this.orderDetail_id = orderDetail_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
