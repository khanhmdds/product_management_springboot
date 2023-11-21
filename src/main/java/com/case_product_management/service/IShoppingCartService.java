package com.case_product_management.service;

import com.case_product_management.model.CartItem;

import java.util.Collection;

public interface IShoppingCartService {

    void add(CartItem item);

    void remove(Long id);

    CartItem update(Long proID, int qty);

    void clear();

    Collection<CartItem> getAllItems();

    int getCount();

    double getTotal();
}
