package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private int pageNum;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean last;
}
