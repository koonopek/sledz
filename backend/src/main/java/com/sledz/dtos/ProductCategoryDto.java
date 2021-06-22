package com.sledz.dtos;


public class ProductCategoryDto {
    public long id;
    public String name;
    public String externalId;
    
    public ProductCategoryDto(long id, String name, String externalId) {
        this.id = id;
        this.name = name;
        this.externalId = externalId;
    }

    public ProductCategoryDto() {};
}
