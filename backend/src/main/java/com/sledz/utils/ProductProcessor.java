package com.sledz.utils;

import java.util.List;

import com.sledz.entities.Product;
import com.sledz.services.ProductProvider.ExternalProduct;


// singleton
public interface ProductProcessor {
    Product convert(List<ExternalProduct> externalProduct);
}
