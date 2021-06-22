package com.sledz.services;

import java.util.List;

import com.sledz.dtos.ProductDto;
import com.sledz.entities.Subscription;

import org.springframework.stereotype.Service;

@Service
public interface ProductService { 
    ProductDto getProductDetails(Long productId);

    List<ProductDto> getSubscribedProducts(Long userId);

    Subscription createSubscription(Long userId, Long productId);

    void removeSubscription(Long userId, Long productId);
}
