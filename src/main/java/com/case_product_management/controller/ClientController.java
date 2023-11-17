package com.case_product_management.controller;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import com.case_product_management.service.ICategoryService;
import com.case_product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return iCategoryService.findAll();
    }

    @GetMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/client/index");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/client/detail");
        modelAndView.addObject("product", product.get());
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
//        if(product.isPresent()){
//
//        }else {
//            return new ModelAndView("/client/error");
//        }
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("keyword") String keyword){
        List<Product> searchResults = iProductService.searchProducts(keyword);
        ModelAndView modelAndView = new ModelAndView("/client/index");
        modelAndView.addObject("products", searchResults);
        return modelAndView;
    }

//    @GetMapping("/searchByCategory")
//    public ModelAndView searchByCategory(@RequestParam("keyword") Long id){
//        Optional<Category> category = iCategoryService.findById(id);
//        Iterable<Product> products = iProductService.findProductsByCategory(category);
//    }
}
