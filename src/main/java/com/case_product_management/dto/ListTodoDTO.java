package com.case_product_management.dto;

public class ListTodoDTO {
    private int newOrder; // số đơn hàng mới
    private int newContact; // số liên hệ mới;
    private int waitingOrder; // số đơn hàng chờ duyệt

    public int getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(int newOrder) {
        this.newOrder = newOrder;
    }

    public int getNewContact() {
        return newContact;
    }

    public void setNewContact(int newContact) {
        this.newContact = newContact;
    }

    public int getWaitingOrder() {
        return waitingOrder;
    }

    public void setWaitingOrder(int waitingOrder) {
        this.waitingOrder = waitingOrder;
    }
}
