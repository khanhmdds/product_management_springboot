package com.case_product_management.service;

import com.case_product_management.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Page<Category> getAllCategoryForPageable(int page, int size);

    List<Category> getAllCategory();

    Category getCategoryById(long id);

    Category save(Category category);

    Category update(Category category);

    void deleteById(long id);
}
