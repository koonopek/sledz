package com.sledz.controllers;

import com.sledz.dtos.ProductsSearchDto;
import com.sledz.services.ProductProvider.ProductQuery;
import com.sledz.services.ProductService;

import com.sledz.services.Searcher.MockSearcher;
import com.sledz.services.Searcher.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class ProductController {

    private final ProductService productService;
    private final Searcher searcher = new MockSearcher();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/{productId}")
    public Object getProduct(@PathVariable(value="productId") Long productId) {
        return this.productService.getProductDetails(productId);
    }

    @PostMapping("products/search")
    public Object searchProduct(@RequestBody ProductsSearchDto productsSearch) {
        return this.searcher.searchProduct(ProductQuery.builder().phrase(productsSearch.name).categoryStr(productsSearch.category).build());
    }
}
