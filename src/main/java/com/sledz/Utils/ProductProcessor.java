package com.sledz.Utils;

import java.util.List;

import com.sledz.ProductProvider.ExternalProduct;
import com.sledz.ProductProvider.InternalProduct;


// singleton
public interface ProductProcessor {
    InternalProduct convert(List<ExternalProduct> externalProduct);
}
