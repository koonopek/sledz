package com.sledz.services.ProductProvider.Allegro;

import com.google.gson.*;
import com.google.gson.JsonObject;
import com.sledz.services.ProductProvider.ExternalProduct;
import com.sledz.services.ProductProvider.ExternalProductProvider;
import com.sledz.services.ProductProvider.ExternalProductProviderAuthenticator;
import com.sledz.services.ProductProvider.ProductQuery;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

        var response = makeReq(parseQuery(query), HttpMethod.GET);
        System.out.println(response.toString());
        JsonArray products = response.getAsJsonObject("items").getAsJsonArray("regular");
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
    private String parseQuery(ProductQuery query)
    {
        var builder = UriComponentsBuilder.fromHttpUrl(_baseUrl+_productEndpoint);
        if(query.categoryId != null) {
            builder.queryParam("category.id", query.categoryId);
        }
        builder.queryParam("phrase", query.phrase != null ? query.phrase : "No phrase" );
        builder.queryParam("limit", query.querySize != null ? query.querySize.toString() : "10");

        return builder.toUriString();
    }
    private JsonObject makeReq(String url, HttpMethod method)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + _authenticator.getToken());
        headers.add("Accept", "application/vnd.allegro.public.v1+json");
        HttpEntity<String> req = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(url, method,req,String.class);

        if(result.getStatusCode() != HttpStatus.OK)
        {
            return JsonNull.INSTANCE.getAsJsonObject();
        }
        return JsonParser.parseString(result.getBody()).getAsJsonObject();
    }
}
