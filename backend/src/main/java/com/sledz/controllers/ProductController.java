package com.sledz.controllers;

import java.util.List;

import com.sledz.dtos.ProductDto;
import com.sledz.dtos.ProductsSearchDto;
import com.sledz.services.ProductService;
import com.sledz.services.ProductProvider.ProductQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/{productId}")
    public ProductDto getProduct(@PathVariable(value = "productId") Long productId) {
        return this.productService.getProductDetails(productId);
    }

    @GetMapping("prodcuts/subscribed")
    public List<ProductDto> getSubsribedProducts(){
        var currentUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return this.productService.getSubscribedProducts(currentUser);
    }

    @PostMapping("product/subscription/{productId}")
    public void createSubscription(@PathVariable(value = "productId") Long productId){
        var currentUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        this.productService.createSubscription(currentUser, productId);
    }

    @DeleteMapping("product/subscription/{productId}")
    public void removeSubscription(@PathVariable(value = "productId") Long productId){
        var currentUser = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        this.productService.removeSubscription(currentUser, productId);
    }

    @PostMapping("products/search")
    public Object searchProduct(@RequestBody ProductsSearchDto productsSearch) {
        return this.productService.searchProduct(ProductQuery.builder().phrase(productsSearch.name).categoryStr(productsSearch.category).build());
    }

}
