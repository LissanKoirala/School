package com.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CoinMarketCapAPI {
    RequestHandler ReqHandler = new RequestHandler();
    private String api_key_token = "d589f7cf-ec3e-4030-8e62-9423e4b48b02";

    public CoinMarketCapAPI(){
    }

    public JsonArray make_request(String request_url) throws Exception {
        return ReqHandler.get(request_url+"&CMC_PRO_API_KEY="+api_key_token);
    }

    public JsonArray crypto_info(String ticker) throws Exception {
        String request_url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/info?symbol="+ticker;
        return make_request(request_url);
    }


}
