package com.sledz.dtos;

import java.util.List;

import com.sledz.entities.Value;

public class ProductDto {
    public long id;
    public String name;
    public String description;
    public List<Value> priceHistory;
    public ProductCategoryDto category;

    public ProductDto(long id, String name, String description, List<Value> priceHistory, ProductCategoryDto category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceHistory = priceHistory;
        this.category = category;
    }

    public ProductDto() {}
}
