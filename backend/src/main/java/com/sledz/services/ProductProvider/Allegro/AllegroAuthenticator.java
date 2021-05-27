package com.sledz.services.ProductProvider.Allegro;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sledz.services.ProductProvider.ExternalProduct;
import com.sledz.services.ProductProvider.ExternalProductProviderAuthenticator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AllegroAuthenticator implements ExternalProductProviderAuthenticator {
    static final String _baseUrl = "https://allegro.pl";
    static final String _authEndpoint = "/auth/oauth/token";

    private String _currentToken = null;
    private String _refreshToken = null;

    private long _expirationDate = 0;


    public String getToken()
    {
        if(!isTokenValid())
        {
            if(_refreshToken != null)
            {
                refreshToken();
            }
            initToken();
        }
        return _currentToken;
    }

    public boolean isTokenValid()
    {
        long currTime = System.currentTimeMillis() / 1000;
        return _expirationDate - currTime > 10;
    }
    private void initToken()
    {

        long currTime = System.currentTimeMillis() / 1000;
        JsonObject res = makeReq("client_credentials",null);
        System.out.println("GOT JSON:" + res.toString());
        _currentToken = res.get("access_token").getAsString();
        if(res.get("refresh_token") == null)
        {
            _refreshToken = null;
        }
        else
        {
            _refreshToken = res.get("refresh_token").getAsString();
        }

        long expiresIn = res.get("expires_in").getAsLong();

        _expirationDate = currTime + expiresIn;
    }

    private void refreshToken()
    {
        long currTime = System.currentTimeMillis() / 1000;
        JsonObject res = makeReq("refresh_token",_refreshToken);
        System.out.println("GOT JSON:" + res.toString());
        _currentToken = res.get("access_token").getAsString();
        if(res.get("refresh_token").isJsonNull())
        {
            _refreshToken = null;
        }
        else
        {
            _refreshToken = res.get("refresh_token").getAsString();
        }
        long expiresIn = res.get("expires_in").getAsLong();

        _expirationDate = currTime + expiresIn;
    }

    private JsonObject makeReq(String grantType,String refreshToken)
    {
        HttpHeaders headers = new HttpHeaders();
        String auth = AllegroSecrets.getKey() + ":" + AllegroSecrets.getSecret();
        headers.add("Authorization", "Basic " + Base64.encodeBase64String(auth.getBytes(StandardCharsets.UTF_8)));


        HttpEntity<String> req = new HttpEntity<>(headers);

        var builder = UriComponentsBuilder.fromHttpUrl(_baseUrl+_authEndpoint)
                .queryParam("grant_type",grantType);
        if(refreshToken != null)
        {
           builder.queryParam("refresh_token", refreshToken);
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,req,String.class);

        if(result.getStatusCode() != HttpStatus.OK)
        {
            throw new RuntimeException("Allegro get token failure code");
        }


        return JsonParser.parseString(result.getBody()).getAsJsonObject();
    }

}
