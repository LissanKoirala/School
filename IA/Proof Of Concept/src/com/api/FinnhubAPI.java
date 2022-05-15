package com.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class FinnhubAPI {
    RequestHandler ReqHandler = new RequestHandler();
    private String api_key_token = "c9tbouqad3i1pjtuimu0";

    public FinnhubAPI(){
    }

    public JsonArray make_request(String request_url) throws Exception {
        return ReqHandler.get(request_url+"&token="+api_key_token);
    }

    public JsonArray company_profile(String ticker) throws Exception {
        String request_url = "https://finnhub.io/api/v1/stock/profile2?symbol="+ticker;
        return make_request(request_url);
    }


}
