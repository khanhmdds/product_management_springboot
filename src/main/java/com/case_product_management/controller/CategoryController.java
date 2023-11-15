package com.case_product_management.controller;

import com.case_product_management.model.Category;
import com.case_product_management.service.ICategoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        iCategoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView listCategories(){
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", iCategoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Category> category = iCategoryService.findById(id);
        if(category.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        }else {
            return new ModelAndView("/error");
        }
    }

    @PostMapping("update")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        iCategoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/update");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Optional<Category> category = iCategoryService.findById(id);
        if(category.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category.get());
            return modelAndView;
        }else {
            return new ModelAndView("error");
        }
    }

    @PostMapping("/delete")
    public String deleteCategory(@ModelAttribute("category") Category category){
        iCategoryService.remove(category.getCategory_id());
        return "redirect:/categories";
    }
}
