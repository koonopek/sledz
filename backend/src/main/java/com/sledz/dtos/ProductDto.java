package com.sledz.dtos;

import java.util.List;

public class ProductDto {
    public long id;
    public String name;
    public String description;
    public List<HistoryValueDto> priceHistory;
    public ProductCategoryDto category;
}
