package com.sledz.services.Searcher;

import com.sledz.dtos.HistoryValueDto;
import com.sledz.dtos.ProductCategoryDto;
import com.sledz.dtos.ProductDto;
import com.sledz.entities.Product;
import com.sledz.services.ProductProvider.ProductQuery;
import com.sledz.utils.Statistic;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class MockSearcher implements Searcher {

    static String[] shorts = {"Pro", "Plus", "Eco", "XL", "XXL", "Pro+", "X", "Plus"};
    static String[] longs = {"Exclusive", "Premium", "Excelsior", "Master", "Supreme"};
    static long day = 60*60*24;
    @Override
    public List<ProductDto> searchProduct(ProductQuery query) {

        var l = new LinkedList<ProductDto>();
        List<String> descs = Statistic.perm(Arrays.asList(longs),Statistic.perm(Arrays.asList(new String[]{query.phrase}),Arrays.asList(shorts),5),5);
        for(int i =0;i<5;i++)
        {
            ProductDto p = new ProductDto();

            p.category = new ProductCategoryDto();
            p.category.externalId = query.categoryStr.concat("-external");
            p.category.id = query.categoryStr.hashCode();
            p.category.name = query.categoryStr;

            p.description = "Details of " + descs.get(i);
            p.name = descs.get(i);
            p.id = p.name.hashCode();

            //DZIĘKUJE PAN JAVA XDDDDDD
            //sugerowany sposób na lambde ze stanem
            var ref = new Object() {
                int q = 100;
            };
            p.priceHistory = Arrays.stream(Statistic.stochd(100, p.category.id % 1000, p.id % 10)).mapToObj(d -> {
                HistoryValueDto h =new HistoryValueDto();
                h.date = "" + ((new Date().getTime()/100)- ref.q*day);
                h.id=100-ref.q;
                h.price = d;
                ref.q--;
                return h;
            }).collect(Collectors.toList());

            l.add(p);
        }

        return l;
    }
}
