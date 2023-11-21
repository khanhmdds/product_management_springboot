package com.case_product_management.service.impl;

import com.case_product_management.model.CartItem;
import com.case_product_management.service.IShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartService implements IShoppingCartService {
    Map<Long, CartItem> maps = new HashMap<>();

    @Override
    public void add(CartItem item){
        CartItem cartItem = maps.get(item.getProductId());
        if(cartItem == null){
            maps.put(item.getProductId(), item);
        }else {
            cartItem.setQty(cartItem.getQty() + 1);
        }
    }

    @Override
    public void remove(Long id){
        maps.remove(id);
    }

    @Override
    public CartItem update(Long proID, int qty){
        CartItem cartItem = maps.get(proID);
        cartItem.setQty(qty);
        return cartItem;
    }

    @Override
    public void clear(){
        maps.clear();
    }

    @Override
    public Collection<CartItem> getAllItems(){
        return maps.values();
    }

    @Override
    public int getCount(){
        return maps.values().size();
    }

    @Override
    public double getTotal(){
        return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
    }
}

