package com.sledz.services.Searcher;

import java.util.List;

import com.sledz.entities.Product;
import com.sledz.services.ProductProvider.ProductQuery;

public interface Searcher {
   List<Product> searchProduct(ProductQuery query);
}
