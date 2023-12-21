package com.case_product_management.controller;

import com.case_product_management.model.CartItem;
import com.case_product_management.model.Order;
import com.case_product_management.model.OrderDetails;
import com.case_product_management.model.Product;
import com.case_product_management.model.dto.OrderForm;
import com.case_product_management.model.response.CheckoutResponse;
import com.case_product_management.model.response.OrderResponse;
import com.case_product_management.repository.IOrderRepository;
import com.case_product_management.service.IOrderDetailsService;
import com.case_product_management.service.IOrderService;
import com.case_product_management.service.IProductService;
import com.case_product_management.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService iShoppingCartService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IOrderDetailsService iOrderDetailsService;

//    @GetMapping("view-cart")
//    public String viewCarts(Model model){
//        model.addAttribute("cartItems", iShoppingCartService.getAllItems());
//        model.addAttribute("total", iShoppingCartService.getTotal());
//        return "/client/cart";
//    }

    @GetMapping("/view")
    public ResponseEntity<Map<String, Object>> viewCarts() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<CartItem> cartItems = new ArrayList<>(iShoppingCartService.getAllItems());
            double total = iShoppingCartService.getTotal();

            response.put("cartItems", cartItems);
            response.put("total", total);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("error", "Error retrieving cart information");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("add/{id}")
//    public String addCart(@PathVariable("id") Long id){
//        Optional<Product> product = iProductService.findById(id);
//        if(product.isPresent()){
//            CartItem item = new CartItem();
//            item.setProductId(product.get().getProduct_id());
//            item.setName(product.get().getProductName());
//            item.setPrice(product.get().getPrice());
//            item.setQty(1);
//            item.setImage(product.get().getImage());
//            iShoppingCartService.add(item);
//        }
//        return "redirect:/clients";
//    }

    @PostMapping("/add/{id}")
    public ResponseEntity<String> addCart(@PathVariable("id") Long id) {
        try {
            Optional<Product> product = iProductService.findById(id);

            if (product.isPresent()) {
                CartItem item = new CartItem();
                item.setProductId(product.get().getProduct_id());
                item.setName(product.get().getProductName());
                item.setPrice(product.get().getPrice());
                item.setQty(1);
                item.setImage(product.get().getImage());

                iShoppingCartService.add(item);

                return new ResponseEntity<>("Item added to cart successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding item to cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("clear")
//    public String clearCart(){
//        iShoppingCartService.clear();
//        return "redirect:/shopping-cart/view-cart";
//    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> cleatCart(){
        try {
            iShoppingCartService.clear();
            return new ResponseEntity<>("Shopping cart cleared successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error clearing shopping cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("remove/{id}")
//    public String removeCart(@PathVariable("id") Long id){
//        iShoppingCartService.remove(id);
//        return "redirect:/shopping-cart/view-cart";
//    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeCartItem(@PathVariable("id") Long id) {
        try {
            iShoppingCartService.remove(id);
            return new ResponseEntity<>("Item removed from the shopping cart successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error removing item from the shopping cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("update")
//    public String updateCart(@RequestParam("id") Long id, @RequestParam("qty") int qty){
//        iShoppingCartService.update(id, qty);
//        return "redirect:/shopping-cart/view-cart";
//    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItem updatedItem) {
        Long id = updatedItem.getProductId();
        int qty = updatedItem.getQty();
        CartItem updatedCartItem = iShoppingCartService.update(id, qty);

        if (updatedCartItem == null) {
            return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Cart item updated successfully", HttpStatus.OK);
        }
    }

//    @GetMapping("/checkout-form")
//    public String showCheckoutForm(Model model){
//        Collection<CartItem> cartItems = iShoppingCartService.getAllItems();
//        model.addAttribute("cartItems", cartItems);
//        model.addAttribute("total", iShoppingCartService.getTotal());
//        model.addAttribute("orderForm", new OrderForm());
//        return "/client/checkout";
//    }

    @GetMapping("/checkout-form")
    public ResponseEntity<CheckoutResponse> showCheckoutForm() {
        Collection<CartItem> cartItems = iShoppingCartService.getAllItems();
        double total = iShoppingCartService.getTotal();
        OrderForm orderForm = new OrderForm();

        CheckoutResponse response = new CheckoutResponse(cartItems, total, orderForm);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/place-order")
//    public String placeOrder(@ModelAttribute OrderForm orderForm, Model model){
//        Collection<CartItem> cartItems = iShoppingCartService.getAllItems();
//        model.addAttribute("orderForm", new OrderForm());
//        Order order = new Order();
//        order.setAddress(orderForm.getAddress());
//        order.setPhoneNumber(orderForm.getPhoneNumber());
//        order.setFullName(orderForm.getFullName());
//        order.setOrderDate(new Date());
//        order.setOrderStatus("Processing");
//        order.setNote(orderForm.getNote());
//        double totalPrice = 10;
//        for(CartItem cartItem : cartItems){
//            totalPrice += iShoppingCartService.getTotal();
//        }
//        order.setTotalPrice(totalPrice);
//        iOrderService.saveOrder(order);
//
//        List<OrderDetails> orderDetailsList = new ArrayList<>();
//        for(CartItem cartItem : cartItems){
//            Optional<Product> optionalProduct = iProductService.findById(cartItem.getProductId());
//            Product proId = (Product) ((Optional<?>) optionalProduct).orElseThrow(() -> new RuntimeException("Product not found"));
//            OrderDetails orderDetails = new OrderDetails();
//            orderDetails.setOrder(order);
//            orderDetails.setProduct(proId);
//            orderDetails.setUnitPrice(cartItem.getPrice());
//            orderDetails.setQuantityOrder(cartItem.getQty());
//
//            orderDetailsList.add(orderDetails);
//            iOrderDetailsService.saveOrderDetails(orderDetails);
//        }
//
//        order.setOrderDetail(orderDetailsList);
//        iShoppingCartService.clear();
//        model.addAttribute("order", order);
//
//        return "redirect:/shopping-cart/success";
//    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderForm orderForm) {
        Collection<CartItem> cartItems = iShoppingCartService.getAllItems();
        Order order = new Order();
        order.setAddress(orderForm.getAddress());
        order.setPhoneNumber(orderForm.getPhoneNumber());
        order.setFullName(orderForm.getFullName());
        order.setOrderDate(new Date());
        order.setOrderStatus("Processing");
        order.setNote(orderForm.getNote());

        double totalPrice = 10; // Initialize total price

        // Calculate total price
        for (CartItem cartItem : cartItems) {
            totalPrice += iShoppingCartService.getTotal();
        }

        order.setTotalPrice(totalPrice);
        iOrderService.saveOrder(order);

        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Optional<Product> optionalProduct = iProductService.findById(cartItem.getProductId());
            Product product = optionalProduct.orElseThrow(() -> new RuntimeException("Product not found"));

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setProduct(product);
            orderDetails.setUnitPrice(cartItem.getPrice());
            orderDetails.setQuantityOrder(cartItem.getQty());

            orderDetailsList.add(orderDetails);
            iOrderDetailsService.saveOrderDetails(orderDetails);
        }

        order.setOrderDetail(orderDetailsList);
        iShoppingCartService.clear();

        // Create a response object to send back to the client
        OrderResponse orderResponse = new OrderResponse(order);

        // Return a ResponseEntity with the response object and the appropriate HTTP status
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

//    @GetMapping("/success")
//    public ModelAndView success(){
//        ModelAndView modelAndView = new ModelAndView("/client/success");
//        return modelAndView;
//    }
}
