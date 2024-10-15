package com.example.demo.service;


import com.example.demo.entity.Category;
import com.example.demo.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(int pageNum, int pageSize, String sortBy, String sortDir);
    Category getCategoryById(Long id);
    Category createCategory(Category newCategory);
    Category updateCategory(Long id, Category updateCategory);
    void deleteCategory(Long id);
}
