package com.asset;

import com.api.AlpacaAPI;
import com.google.gson.JsonArray;

public class NewsData {
    AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();

    public NewsData(){
    }

    // TODO: Add this source for news as well : https://finnhub.io/docs/api/market-news | https://finnhub.io/docs/api/company-news
    public JsonArray get(String ticker) throws Exception {
        return AlpacaAPIHandler.get_news(ticker);
    }

    // Insider Info
    // TODO: Insider Transaction : https://finnhub.io/docs/api/insider-transactions
    // TODO: Insider Sentiment : https://finnhub.io/docs/api/insider-sentiment

    // TODO: Add get with timeframe so the program can look for today's sentiment and some other timeframe sentiment
}
