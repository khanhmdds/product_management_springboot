package com.case_product_management.controller;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import com.case_product_management.service.ICategoryService;
import com.case_product_management.service.IProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return iCategoryService.findAll();
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("/product/create");
        }
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView listProducts(){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", iProductService.findAll());
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        if(product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/update");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("/update")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product){
        iProductService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/update");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Product> product = iProductService.findById(id);
        if(product.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }else {
            return new ModelAndView("error");
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        iProductService.remove(product.getProduct_id());
        return "redirect:/products";
    }
}
