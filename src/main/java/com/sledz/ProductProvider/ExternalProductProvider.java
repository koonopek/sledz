package com.sledz.ProductProvider;

import java.util.List;

public interface ExternalProductProvider {   
    List<ExternalProduct> searchProducts(ProductQuery query);
}