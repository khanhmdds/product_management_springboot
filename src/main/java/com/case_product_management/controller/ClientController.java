package com.case_product_management.controller;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import com.case_product_management.service.ICategoryService;
import com.case_product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return iCategoryService.findAll();
    }

//    @GetMapping("")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView("/client/index");
//        modelAndView.addObject("products", iProductService.findAll());
//        return modelAndView;
//    }

    @GetMapping("")
    public ResponseEntity<Iterable<Product>> listProducts(){
        return new ResponseEntity<>(iProductService.findAll(), HttpStatus.OK);
    }

//    @GetMapping("/detail/{id}")
//    public ModelAndView detail(@PathVariable Long id){
//        Optional<Product> product = iProductService.findById(id);
//        ModelAndView modelAndView = new ModelAndView("/client/detail");
//        modelAndView.addObject("product", product.get());
//        modelAndView.addObject("products", iProductService.findAll());
//        return modelAndView;
//    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getProductDetail(@PathVariable Long id) {
        Optional<Product> product = iProductService.findById(id);
        if (product.isPresent()) {
//            String productDetailUrl = "/"
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/search")
//    public ModelAndView search(@RequestParam("keyword") String keyword){
//        List<Product> searchResults = iProductService.searchProducts(keyword);
//        ModelAndView modelAndView = new ModelAndView("/client/index");
//        modelAndView.addObject("products", searchResults);
//        return modelAndView;
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam("keyword") String keyword) {
        List<Product> searchResults = iProductService.searchProducts(keyword);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

//    @GetMapping("/searchByCategory")
//    public ModelAndView searchByCategory(@RequestParam("category") Long id){
//        Optional<Category> optionalCategory = iCategoryService.findById(id);
//        if(optionalCategory.isPresent()){
//            Category category = optionalCategory.get();
//            List<Product> products = iProductService.findProductsByCategory(category);
//            Iterable<Product> allProducts = iProductService.findAll();
//            ModelAndView modelAndView = new ModelAndView("/client/index");
//            modelAndView.addObject("products", products);
//            modelAndView.addObject("all", allProducts);
//            return modelAndView;
//        }else {
//            return new ModelAndView("/client/error");
//        }
//    }

    @GetMapping("/getCategory")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = (List<Category>) iCategoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Product>> searchByCategory(@RequestParam("categoryId") Long categoryId) {
        Optional<Category> optionalCategory = iCategoryService.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            List<Product> products = iProductService.findProductsByCategory(category);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/error")
//    public ModelAndView error(){
//        ModelAndView modelAndView = new ModelAndView("/client/error");
//        modelAndView.addObject("message", "Not Found");
//        return modelAndView;
//    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        String errorMessage = "Not Found";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
