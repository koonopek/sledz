package com.sledz.services.ProductProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public interface ExternalProductProvider {
    List<ExternalProduct> searchProducts(ProductQuery query);
    public String getCategoryId(String name);
    public Set<String> getCategoryNames();
    public String getCategoryName(String externalId);
}