package com.sledz.utils;

import java.util.*;

import com.sledz.entities.Category;
import com.sledz.entities.Product;
import com.sledz.entities.Value;
import com.sledz.repositories.ProductRepository;
import com.sledz.services.ProductProvider.ExternalProduct;
import com.sledz.services.ProductProvider.ExternalProductProvider;

public class ProductProcessor {
    public static Product convert(List<ExternalProduct> externalProducts, ProductRepository repo, ExternalProductProvider provider){
        if(externalProducts.isEmpty()) return null;

        //get category
        Set<Category> cats= new HashSet<>();
        repo.findAll().forEach(p -> cats.add(p.category));
        var optCat = cats.stream().filter(c->c.externalId == externalProducts.get(0).getCategoryId()).findAny();
        Category pcat=null;

        if(optCat.isEmpty())
        {
            pcat = new Category();
            pcat.externalId = externalProducts.get(0).getCategoryId();
            pcat.name = provider.getCategoryName(pcat.externalId);
        }
        else
        {
            pcat = optCat.get();
        }

        //get value
        var val = new Value();
        val.value = Statistic.average(externalProducts);
        val.date = Calendar.getInstance().getTime().getTime();


        Product ret = new Product();

        ret.name = externalProducts.get(0).getName();
        ret.category = pcat;
        ret.description = "Brak";
        ret.valueHistory.add(val);

        return ret;
    }
}
