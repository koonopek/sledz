package com.sledz.services.ProductProvider.Allegro;


import com.sledz.services.ProductProvider.ExternalProduct;
import com.sledz.services.ProductProvider.ProductQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllegroProductProviderTest {

    @Test
    void searchProducts() {
        AllegroProductProvider provider = new AllegroProductProvider(new AllegroAuthenticator());
        List<ExternalProduct> p = provider.searchProducts(ProductQuery.builder()
                .querySize(5)
                .phrase("One Plus")
                .build());
        System.out.println("Got " + p.size() + " products");
        for(ExternalProduct prod : p)
        {
            System.out.println(prod.getName() + ":" + prod.getPrice());
        }
    }
}