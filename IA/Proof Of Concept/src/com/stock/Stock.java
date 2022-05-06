package com.stock;

import com.api.AlpacaAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Stock {
    public String ticker;
    public String name;
    public String exchange;
    public String type;

    public JsonObject info;

    // points to a class of price data and news data, they will be created on the constructor of the class
    // data will be parsed and stored in a way that can be processed later
    public String[] price_data;
    public String[] news_data;

    public Float[][] historical_data;

    private AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();
    private HistoricalData HistoricalData = new HistoricalData();



    // instantiate the stock with getting useful data automatically
    public Stock(String ticker) throws Exception {
        JsonArray data = AlpacaAPIHandler.ticker_info(ticker);

        String res = data.get(0).toString();
        JsonObject response = new JsonParser().parse(res).getAsJsonObject();
        info = response;

        try {
            this.ticker = response.get("symbol").getAsString();
            this.name = response.get("name").getAsString();
            this.exchange = response.get("exchange").getAsString();
            this.type = response.get("class").getAsString();
            getHistorical_data();


        } catch (Exception e){ // if stock doesn't exists

            System.out.println(e);

            String error_message = "asset not found for " + ticker;
            String message = response.get("message").getAsString();

            if (error_message.equals(message)) {
                System.out.println("Stock Not Found");
            } else {
                System.out.print(message);
            }
        }
    }

    // gets and updates the historical data, can also be accessed directly by the shared variable
    public Float[][] getHistorical_data() throws Exception {
        this.historical_data = HistoricalData.get(ticker);
        return historical_data;
    }

    public JsonArray getNewsData() throws Exception {
        NewsData newsData = new NewsData();
        return newsData.get(ticker);
    }

}
