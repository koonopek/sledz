package com.sledz.services.ProductProvider.Allegro;

import com.google.gson.*;
import com.google.gson.JsonObject;
import com.sledz.services.ProductProvider.ExternalProduct;
import com.sledz.services.ProductProvider.ExternalProductProvider;
import com.sledz.services.ProductProvider.ExternalProductProviderAuthenticator;
import com.sledz.services.ProductProvider.ProductQuery;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Realizacja interfejsu ExternalProductProvider dla allegro.pl
 */
public class AllegroProductProvider implements ExternalProductProvider {

    static final String _baseUrl = "https://api.allegro.pl";
    static final String _productEndpoint = "/offers/listing";
    private ExternalProductProviderAuthenticator _authenticator;

    AllegroProductProvider(ExternalProductProviderAuthenticator authenticator)
    {
        _authenticator = authenticator;
    }

    @Override
    public List<ExternalProduct> searchProducts(ProductQuery query) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + _authenticator.getToken());
        headers.add("Accept", "application/vnd.allegro.public.v1");
        headers.add("Accept", "application/json");



        HttpEntity<String> req = new HttpEntity<>(headers);
        Map<String,String> args = parseQuery(query);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(_baseUrl+_productEndpoint, HttpMethod.GET,req,String.class,args);

        if(result.getStatusCode() != HttpStatus.OK)
        {
            return new LinkedList<>();
        }


        JsonObject response = JsonParser.parseString(result.getBody()).getAsJsonObject();

        JsonArray products = response.getAsJsonObject("items").getAsJsonArray("promoted");
        List<ExternalProduct> ret = new LinkedList<>();
        for(JsonElement elem : products)
        {
            ret.add(new AllegroProduct(elem.getAsJsonObject()));
        }
        return ret;
    }

    /**
     * Parse standard product query
     * productId is not supported
     * @param query standard product query object
     * @return request parameter map
     */
    private Map<String,String> parseQuery(ProductQuery query)
    {
        Map<String,String> args = new HashMap<>();
        if(query.categoryId != null) {
            args.put("category.id", query.categoryId);
        }
        args.put("phrase", query.phrase != null ? query.phrase : "No phrase" );
        args.put("limit", query.querySize != null ? query.querySize.toString() : "1");

        return args;
    }
}
