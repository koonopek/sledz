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


import javax.annotation.Nullable;
import java.util.*;

/**
 * Realizacja interfejsu ExternalProductProvider dla allegro.pl
 */
public class AllegroProductProvider implements ExternalProductProvider {

    static final String _baseUrl = "https://api.allegro.pl";
    static final String _productEndpoint = "/offers/listing";
    static final String _categoryEndpoint = "/sale/categories";
    private ExternalProductProviderAuthenticator _authenticator;
    private Map<String,String> categories = new HashMap<>();

    AllegroProductProvider(ExternalProductProviderAuthenticator authenticator)
    {
        _authenticator = authenticator;
        updateCategories();
    }

    @Nullable
    @Override
    public String getCategoryId(String name){
        return categories.get(name);
    }

    @Override
    public Set<String> getCategoryNames(){
        return categories.keySet();
    }

    @Override
    public String getCategoryName(String externalId) {
        return categories.get(externalId);
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
        System.out.println(result.getBody());
        return JsonParser.parseString(result.getBody()).getAsJsonObject();
    }

    private void updateCategories()
    {
        //kategoie są ułożone w drzewo



        List<String> ids = new LinkedList<>();
        ids.add("954b95b6-43cf-4104-8354-dea4d9b10ddf"); //base key;
        do{
            var obj = makeReq(UriComponentsBuilder.fromHttpUrl(_baseUrl+_categoryEndpoint)
                    .queryParam("parent.id",ids.remove(0)).toUriString(),HttpMethod.GET);
            var arr = obj.getAsJsonArray("categories");
            for(JsonElement el : arr){
                var id = el.getAsJsonObject().get("id").getAsString();
                var name = el.getAsJsonObject().get("name").getAsString();
                var leaf = el.getAsJsonObject().get("leaf").getAsBoolean();

                if(!leaf) ids.add(id);
                categories.put(name,id);
            }
        }while(!ids.isEmpty());

       //todo make this a property file ->  categories.forEach( (a,b) -> System.out.println(a+"="+b) );
    }
}
