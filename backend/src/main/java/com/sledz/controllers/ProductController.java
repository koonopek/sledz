package com.sledz.controllers;

import com.sledz.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/{productId}")
    public Object getProduct(@PathVariable(value="productId") Long productId) {
        return this.productService.getProductDetails(productId);
    }

    // itd.
}
