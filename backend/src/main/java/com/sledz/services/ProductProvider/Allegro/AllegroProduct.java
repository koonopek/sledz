package com.sledz.services.ProductProvider.Allegro;

import com.sledz.services.ProductProvider.ExternalProduct;

import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.*;

/**
 * Realizacja ExternalProduct dla Allegro
 */
public class AllegroProduct implements ExternalProduct {
    private JsonObject _json;
    private static final String[] priceKeys = { "sellingMode", "price", "amount"};
    private static final String[] categoryIdKeys = { "category", "id"};
    private static final String[] nameKeys = {"name"};
    AllegroProduct(JsonObject json)
    {
        _json = json;
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal ret = null;
        try {
            ret = _json.getAsJsonObject(priceKeys[0])
                    .getAsJsonObject(priceKeys[1])
                    .getAsJsonPrimitive(priceKeys[2])
                    .getAsBigDecimal();
        }catch (Exception e) {
            ret = null;
        }
        return ret;
    }

    @Override
    public String getName() {
        String ret = null;
        try {
            ret = _json.getAsJsonPrimitive(nameKeys[0])
                .getAsString();
        }catch (Exception e) {
            ret = null;
        }
        return ret;
    }

    @Override
    public String getCategoryId() {
        String ret = null;
        try {
            ret = _json.getAsJsonObject(categoryIdKeys[0])
                    .getAsJsonPrimitive(categoryIdKeys[1])
                    .getAsString();
        }catch (Exception e) {
            ret = null;
        }
        return ret;
    }

    @Override
    public String getImageUrl() {
        String ret = null;
        try {
            ret = _json.getAsJsonArray("images")
                    .get(0)
                    .getAsString();
        }
        catch (Exception e) {
            ret = null;
        }
        return ret;
    }
}
