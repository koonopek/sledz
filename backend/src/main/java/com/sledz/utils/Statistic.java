package com.sledz.utils;

import com.sledz.services.ProductProvider.ExternalProduct;

import java.util.List;

public class Statistic {    

    public static Double average(List<ExternalProduct> extProdList) {
        return extProdList.stream().map(e -> e.getPrice().doubleValue()).reduce((a,b)->a+b).get()/extProdList.size();
    }
}
