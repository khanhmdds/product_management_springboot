package com.case_product_management.dto;

public class SearchOrderObject {
    private String orderStatus;
    private String fromDay;
    private String toDay;

    public SearchOrderObject() {
        orderStatus = "";
        fromDay = "";
        toDay = "";
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getFromDay() {
        return fromDay;
    }

    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }
}
