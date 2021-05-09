package com.sledz.services;

import org.springframework.stereotype.Service;

@Service
public class MockProductService implements ProductService {

    @Override
    public Object getProductDetails(Long productId) {
        return null;
    }

}
