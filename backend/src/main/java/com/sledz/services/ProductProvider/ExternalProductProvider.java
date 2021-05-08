package com.sledz.services.ProductProvider;

import java.util.List;
public interface ExternalProductProvider {
    List<ExternalProduct> searchProducts(ProductQuery query);
}