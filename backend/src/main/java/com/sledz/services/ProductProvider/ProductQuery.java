package com.sledz.services.ProductProvider;

import lombok.Builder;

/**
 * Ta kla
 */
@Builder
public class ProductQuery { 
    String categoryId;
    String phrase;
    Integer productId;
    Integer querySize;
 }
