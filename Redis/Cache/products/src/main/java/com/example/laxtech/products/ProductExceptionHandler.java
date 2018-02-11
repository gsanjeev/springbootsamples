package com.example.laxtech.products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ProductExceptionHandler {

    private Logger log = LoggerFactory.getLogger(ProductExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post Not Found")
    @ExceptionHandler(ProductNotFoundException.class)
    public void handleProductNotFound(HttpServletRequest request, Exception ex) {
        log.error("{} : {}", ex.getMessage(), request.getRequestURI());
    }
}
