package com.sledz.services.ProductProvider.Allegro;

import com.sledz.services.ProductProvider.ExternalProduct;
import com.sledz.services.ProductProvider.ExternalProductProvider;
import com.sledz.services.ProductProvider.ExternalProductProviderAutenthicator;
import com.sledz.services.ProductProvider.ProductQuery;
import org.json.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AllegroProductProvider implements ExternalProductProvider {

    final String _baseUrl =
    private ExternalProductProviderAutenthicator _autenthicator;

    AllegroProductProvider(ExternalProductProviderAutenthicator autenthicator)
    {
        _autenthicator = autenthicator;
    }

    @Override
    public List<ExternalProduct> searchProducts(ProductQuery query) {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort + "/employees";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

//Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("employeeList"));
    }
}
