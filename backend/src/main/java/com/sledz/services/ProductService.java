package com.sledz.services;

import org.springframework.stereotype.Service;

@Service
public interface ProductService { 
    // to be replaced with proper DTO
    Object getProductDetails(Long productId);
 }
