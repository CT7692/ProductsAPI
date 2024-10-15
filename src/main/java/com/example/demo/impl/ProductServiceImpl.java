package com.example.demo.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductAPIException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Long categoryID, Product newProduct) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        newProduct.setCategory(category);
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long categoryID, Long productID, Product updateProduct) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        Product product = productRepository
                .findById(productID)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productID));

        if(!updateProduct.getCategory().getId().equals(category.getId())) {
            throw new ProductAPIException(HttpStatus.NOT_FOUND, "Product does not belong to category.");
        }

        product.setProductName(updateProduct.getProductName());
        product.setPrice(updateProduct.getPrice());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long categoryID, Long productID) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        Product product = productRepository
                .findById(productID)
                .orElseThrow(() -> new ResourceNotFoundException("Product ID", "Product", productID));

        if(!product.getCategory().getId().equals(category.getId())) {
            throw new ProductAPIException(HttpStatus.NOT_FOUND, "Product does not belong in the category.");
        }

        productRepository.delete(product);
    }

    @Override
    public Product getProductByID(Long categoryID, Long productID) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryID));

        Product product = productRepository
                .findById(productID)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Product ID", productID));

        if(!product.getCategory().getId().equals(category.getId())) {
            throw new ProductAPIException(HttpStatus.NOT_FOUND, "Product does not belong in category.");
        }

        return product;
    }
}
