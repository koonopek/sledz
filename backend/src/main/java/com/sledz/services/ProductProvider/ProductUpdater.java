package com.sledz.services.ProductProvider;

import com.sledz.entities.Product;
import com.sledz.repositories.ProductRepository;

public interface ProductUpdater {
    void setRepository(ProductRepository repo);
    void setProvider(ExternalProductProvider provider);
    public Product addNewProduct(ProductQuery query);

    public void updateProduct(long productId);

    public void updateAll();
}
