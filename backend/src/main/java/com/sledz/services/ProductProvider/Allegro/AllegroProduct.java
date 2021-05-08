package com.sledz.services.ProductProvider.Allegro;

import com.sledz.services.ProductProvider.ExternalProduct;
import org.json.JSONObject;

import java.awt.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AllegroProduct implements ExternalProduct {
    private JSONObject _json;
    private static final HashMap<String,Object> VOID_MAP = new HashMap<>();
    AllegroProduct(JSONObject json)
    {
        _json = json;
    }

    @Override
    public BigDecimal getPrice() {

        j = getObject(_json.optJSONObject("sellingMode"),"price")
    }

    private static JSONObject getObject(JSONObject json,String key){
        return json != null ? json.optJSONObject(key) : null;
    }
}
