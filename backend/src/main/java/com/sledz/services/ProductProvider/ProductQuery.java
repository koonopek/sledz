package com.sledz.services.ProductProvider;

import lombok.Builder;

/**
 * Ta klasa służy do podawania parametrów do wyszukiwania produktów
 * Jak używać: https://projectlombok.org/features/Builder
 */
@Builder
public class ProductQuery { 
    public String categoryId;
    public String phrase;
    public Integer productId;
    public Integer querySize;
    public String categoryStr;
 }
