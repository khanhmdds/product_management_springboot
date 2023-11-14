package com.case_product_management.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    @OneToMany(mappedBy = "orderDetails")
    private List<OrderDetails> orderDetail;

    private String address;
    private String phoneNumber;
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date orderDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date deliveryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date receiveDate;

    private String orderStatus;
    private String note;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(optional = true)
//    @JoinColumn(name = "ma_shipper")
//    private NguoiDung shipper;

    private double totalPrice;

    public Order() {
    }

    public Order(long order_id, List<OrderDetails> orderDetail, String address, String phoneNumber, String fullName, Date orderDate, Date deliveryDate, Date receiveDate, String orderStatus, String note, User user, double totalPrice) {
        this.order_id = order_id;
        this.orderDetail = orderDetail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.receiveDate = receiveDate;
        this.orderStatus = orderStatus;
        this.note = note;
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public List<OrderDetails> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetails> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
