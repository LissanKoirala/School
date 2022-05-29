package com.asset;

import com.api.AlpacaAPI;
import com.google.gson.JsonObject;

public class Calendar {
    static AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();

    public Calendar(){
    }
    
    // returns is_open=true: next_close, or is_open=false: next_open 
    public static String[] isMarketOpen() throws Exception {
        JsonObject response = AlpacaAPIHandler.clock().get(0).getAsJsonObject();
        String is_open = response.get("is_open").getAsString();

        String next_open = response.get("next_open").getAsString();
        String next_close = response.get("next_close").getAsString();

        if (is_open.equals("true")){
            return new String[]{"Market Open", next_close};
        } else {
            return new String[]{"Market Closed", next_open};
        }
    }
}

// TODO: https://finnhub.io/docs/api/earnings-calendar
