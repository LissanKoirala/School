package com.stock;

import com.api.AlpacaAPI;
import com.google.gson.JsonArray;

public class NewsData {
    AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();

    public NewsData(){
    }

    public JsonArray get(String ticker) throws Exception {
        return AlpacaAPIHandler.get_news(ticker);
    }

    // TODO: Add get with timeframe so the program can look for today's sentiment and some other timeframe sentiment
}
