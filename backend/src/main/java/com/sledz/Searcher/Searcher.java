package com.sledz.Searcher;

import com.sledz.ProductProvider.InternalProduct;
import com.sledz.ProductProvider.ProductQuery;

import java.util.List;

public interface Searcher {
   List<InternalProduct> searchProduct(ProductQuery query);
}
