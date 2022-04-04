package com.company;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class Stock {
    public String ticker;
    public String name;
    public String exchange;
    public String type;

    // points to a class of price data and news data, they will be created on the constructor of the class
    // data will be parsed and stored in a way that can be processed later
    public String[] price_data;
    public String[] news_data;

    public JsonArray historical_data;

    private AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();

    public Stock(String ticker) throws Exception {
        JsonArray data = AlpacaAPIHandler.ticker_info(ticker);

        String res = data.get(0).toString();
        JsonObject response = new JsonParser().parse(res).getAsJsonObject();
        System.out.println(response);

        try {
            this.ticker = response.get("symbol").getAsString();
            this.name = response.get("name").getAsString();
            this.exchange = response.get("exchange").getAsString();
            this.type = response.get("class").getAsString();

        } catch (Exception e){ // if stock doesn't exists

            System.out.println(e);

            String error_message = "asset not found for " + ticker;

            String message = response.get("message").getAsString();

            if (error_message.equals(message)) {
                System.out.println("Stock Not Found");
            } else {
                System.out.printf(message);
            }

        }


    }

    public void getHistorical_data() {
        // this.historical_data =
    }

}
