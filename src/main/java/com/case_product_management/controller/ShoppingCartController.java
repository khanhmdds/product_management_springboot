package com.case_product_management.controller;

import com.case_product_management.model.CartItem;
import com.case_product_management.model.Order;
import com.case_product_management.model.OrderDetails;
import com.case_product_management.model.Product;
import com.case_product_management.model.dto.OrderForm;
import com.case_product_management.repository.IOrderRepository;
import com.case_product_management.service.IOrderDetailsService;
import com.case_product_management.service.IOrderService;
import com.case_product_management.service.IProductService;
import com.case_product_management.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService iShoppingCartService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IOrderDetailsService iOrderDetailsService;

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

    @GetMapping("/checkout-form")
    public String showCheckoutForm(Model model){
        Collection<CartItem> cartItems = iShoppingCartService.getAllItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", iShoppingCartService.getTotal());
        model.addAttribute("orderForm", new OrderForm());
        return "/client/checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute OrderForm orderForm, Model model){
        Collection<CartItem> cartItems = iShoppingCartService.getAllItems();
        model.addAttribute("orderForm", new OrderForm());
        Order order = new Order();
        order.setAddress(orderForm.getAddress());
        order.setPhoneNumber(orderForm.getPhoneNumber());
        order.setFullName(orderForm.getFullName());
        order.setOrderDate(new Date());
        order.setOrderStatus("Processing");
        order.setNote(orderForm.getNote());
        double totalPrice = 10;
        for(CartItem cartItem : cartItems){
            totalPrice += iShoppingCartService.getTotal();
        }
        order.setTotalPrice(totalPrice);
        iOrderService.saveOrder(order);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for(CartItem cartItem : cartItems){
            Optional<Product> optionalProduct = iProductService.findById(cartItem.getProductId());
            Product proId = (Product) ((Optional<?>) optionalProduct).orElseThrow(() -> new RuntimeException("Product not found"));
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setProduct(proId);
            orderDetails.setUnitPrice(cartItem.getPrice());
            orderDetails.setQuantityOrder(cartItem.getQty());

            orderDetailsList.add(orderDetails);
            iOrderDetailsService.saveOrderDetails(orderDetails);
        }

        order.setOrderDetail(orderDetailsList);
        iShoppingCartService.clear();
        model.addAttribute("order", order);

        return "redirect:/shopping-cart/view-cart";
    }
}
