package com.example.demo.impl;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CategoryResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getAllCategories(int pageNum, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        Page<Category> categories = categoryRepository.findAll(pageable);

        List<Category> categoryList = categories.getContent();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryList);
        categoryResponse.setPageNum(categories.getNumber());
        categoryResponse.setTotalPages(categories.getTotalPages());
        categoryResponse.setLast(categories.isLast());

        return categoryResponse;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", id));
    }

    @Override
    public Category createCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(Long id, Category updateCategory) {
        Category savedCategory = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", id));

        savedCategory.setName(updateCategory.getName());

        return categoryRepository.save(savedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", id));
        categoryRepository.delete(category);
    }
}
