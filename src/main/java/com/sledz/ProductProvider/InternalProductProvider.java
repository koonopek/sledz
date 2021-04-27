package com.sledz.ProductProvider;

public interface InternalProductProvider {
    public ProductDetails getProductDetails(int productId);

    public void addSubscription(int userId, int productId);

    public void deleteSubscription(int userId, int productId);
}