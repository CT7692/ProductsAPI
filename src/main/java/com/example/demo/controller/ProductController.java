package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.payload.Response;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{categoryID}/products")
    public ResponseEntity<Product> createProduct(@PathVariable("categoryID") Long categoryID,
                                                 @RequestBody Product newProduct) {
        Product product = productService.addProduct(categoryID, newProduct);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/{categoryID}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryID(@PathVariable("categoryID") Long categoryID) {

        List<Product> products = productService.getProductByCategoryID(categoryID);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{categoryID}/products/{productID}")
    public ResponseEntity<Product> getProductByID(@PathVariable("categoryID") Long categoryID,
                                                  @PathVariable("productID") Long productID) {
        Product product = productService.getProductByID(categoryID, productID);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{categoryID}/products/{productID}")
    public ResponseEntity<Product> updateProduct(@PathVariable("categoryID") Long categoryID,
                                                 @PathVariable("productID") Long productID,
                                                 @RequestBody Product updateProduct) {
        Product product = productService.updateProduct(categoryID, productID, updateProduct);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{categoryID}/products/{productID}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("categoryID") Long categoryID,
                                              @PathVariable("productID") Long productID) {
        productService.deleteProduct(categoryID, productID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
