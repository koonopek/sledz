package com.sledz.ProductProvider;

public interface ProductUpdater {
    public InternalProduct addNewProduct(ProductQuery query);

    public void updateProduct(int productId);
}
