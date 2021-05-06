package com.sledz.services.ProductProvider;

import com.sledz.dtos.ProductDetails;

public interface ProductProvider {
    public ProductDetails getProductDetails(int productId);

    public void addSubscription(int userId, int productId);

    public void deleteSubscription(int userId, int productId);
}