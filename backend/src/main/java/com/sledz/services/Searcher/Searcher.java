package com.sledz.services.Searcher;

import java.util.List;

import com.sledz.dtos.ProductDto;
import com.sledz.entities.Product;
import com.sledz.services.ProductProvider.ProductQuery;

public interface Searcher {
   List<ProductDto> searchProduct(ProductQuery query);
}
