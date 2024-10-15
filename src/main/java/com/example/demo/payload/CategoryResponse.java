package com.example.demo.payload;

import java.util.List;
import com.example.demo.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryResponse extends Response{
    private List<Category> content;
}
