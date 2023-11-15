package com.case_product_management.repository;

import com.case_product_management.model.Category;
import com.case_product_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);
}
