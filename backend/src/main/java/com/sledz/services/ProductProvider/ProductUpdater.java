package com.sledz.services.ProductProvider;

import com.sledz.entities.Product;

public interface ProductUpdater {
    public Product addNewProduct(ProductQuery query);

    public void updateProduct(int productId);
}
