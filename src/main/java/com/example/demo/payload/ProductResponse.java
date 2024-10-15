package com.example.demo.payload;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends Response {
    private List<Product> content;
}
