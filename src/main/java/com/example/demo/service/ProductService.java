package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByCategoryID(Long categoryID);
    Product addProduct(Long categoryID, Product newProduct);
    Product updateProduct(Long categoryID, Long productID, Product updateProduct);
    void deleteProduct(Long categoryID, Long productID);
    Product getProductByID(Long categoryID, Long productID);
}
