package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ProductAPIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public ProductAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ProductAPIException(String customMessage, HttpStatus httpStatus, String message) {
        super(customMessage);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
