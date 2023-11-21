package com.case_product_management.service.impl;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import com.case_product_management.repository.IProductRepository;
import com.case_product_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category){
        return iProductRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> searchProducts(String keyword){
        return iProductRepository.findByProductNameContaining(keyword);
    }

    @Override
    public List<Product> findProductsByCategory(Category category){
        return iProductRepository.findProductsByCategory(category);
    }
}
