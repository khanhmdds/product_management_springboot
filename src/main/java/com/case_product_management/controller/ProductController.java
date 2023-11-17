package com.case_product_management.controller;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import com.case_product_management.service.ICategoryService;
import com.case_product_management.service.IProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView saveProduct(@ModelAttribute("product") Product product){
//        Optional<Category> optionalCategory = iCategoryService.findById(categoryId);
//        product.setCategory(optionalCategory.get());
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
            ModelAndView modelAndView = new ModelAndView("/product/list");
            modelAndView.addObject("product", product.get());
            return modelAndView;
        }else {
            return new ModelAndView("error");
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        Long productId = product.getProduct_id();
//        if(productId == null || productId <= 0){
//            System.out.println("Id k hop le");
//            return "redirect:/products";
//        }
        iProductService.remove(productId);
        return "redirect:/products";
    }
}
