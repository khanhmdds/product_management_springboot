package com.case_product_management.repository;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContaining(String keyword);

    Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> findProductsByCategory(Category category);
}
