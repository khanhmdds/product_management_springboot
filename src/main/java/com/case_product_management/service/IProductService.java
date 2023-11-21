package com.case_product_management.service;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);
    void remove(Long id);
    List<Product> searchProducts(String keyword);
    Iterable<Product> findAllByCategory(Category category);
    List<Product> findProductsByCategory(Category category);
}
