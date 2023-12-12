package com.case_product_management.model.response;

import com.case_product_management.model.CartItem;
import com.case_product_management.model.dto.OrderForm;

import java.util.Collection;

public class CheckoutResponse {
    private Collection<CartItem> cartItems;
    private double total;
    private OrderForm orderForm;

    public CheckoutResponse() {
    }

    public CheckoutResponse(Collection<CartItem> cartItems, double total, OrderForm orderForm) {
        this.cartItems = cartItems;
        this.total = total;
        this.orderForm = orderForm;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotal() {
        return total;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setCartItems(Collection<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }
}
