package com.sledz.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.sledz.entities.Category;
import com.sledz.entities.Product;
import com.sledz.entities.Value;

public class ProductDto {
    public long id;
    public String name;
    public String description;
    public List<ValueDto> priceHistory;
    public ProductCategoryDto category;

    public ProductDto(long id, String name, String description, List<ValueDto> priceHistory, ProductCategoryDto category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceHistory = priceHistory;
        this.category = category;
    }

    public ProductDto(Product p)
    {
        this(p.id,p.name,p.description,p.valueHistory.stream().map( s -> new ValueDto(s)).collect(Collectors.toList()),new ProductCategoryDto(p.category.id,p.category.externalId,p.category.name));
    }

    public ProductDto() {}
}
