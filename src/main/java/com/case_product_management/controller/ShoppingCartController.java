package com.case_product_management.controller;

import com.case_product_management.model.CartItem;
import com.case_product_management.model.Product;
import com.case_product_management.service.IProductService;
import com.case_product_management.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService iShoppingCartService;
    @Autowired
    private IProductService iProductService;
    @GetMapping("view-cart")
    public String viewCarts(Model model){
        model.addAttribute("cartItems", iShoppingCartService.getAllItems());
        model.addAttribute("total", iShoppingCartService.getTotal());
        return "/client/cart";
    }

    @GetMapping("add/{id}")
    public String addCart(@PathVariable("id") Long id){
        Optional<Product> product = iProductService.findById(id);
        if(product.isPresent()){
            CartItem item = new CartItem();
            item.setProductId(product.get().getProduct_id());
            item.setName(product.get().getProductName());
            item.setPrice(product.get().getPrice());
            item.setQty(1);
            item.setImage(product.get().getImage());
            iShoppingCartService.add(item);
        }
        return "redirect:/clients";
    }

    @GetMapping("clear")
    public String clearCart(){
        iShoppingCartService.clear();
        return "redirect:/shopping-cart/view-cart";
    }

    @GetMapping("remove/{id}")
    public String removeCart(@PathVariable("id") Long id){
        iShoppingCartService.remove(id);
        return "redirect:/shopping-cart/view-cart";
    }

    @PostMapping("update")
    public String updateCart(@RequestParam("id") Long id, @RequestParam("qty") int qty){
        iShoppingCartService.update(id, qty);
        return "redirect:/shopping-cart/view-cart";
    }
}
