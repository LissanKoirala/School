package com.asset;

import com.api.AlpacaAPI;
import com.api.FinnhubAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// TODO: Probably use polygon.io to get the data instead of finnhub, it seems to be really show sometimes... or just create a hashmap to store the classes, this might be a better solution, so load things while use it loggin in etc
// TODO: Add Inheritance to this class for crypto and forex, so that their symbols are dealt on their own class, and override methods to get info about them and it's different instead of bunch of ifs and try catch in the main stock class, this means you add inheritance and polymorphism and also will make it a lot cleaner and easier to deal with
// PROBLEM: How to know if it's what? When you read from watchlist, in the for loop which one do you call? Or have a method or a class do that for you?
public class Asset {
    public String ticker;
    public String name;
    public String exchange;
    public String type;

    // TODO: might save the icon the a local directory, then get it from there, if not then call this url, will allow to add icons manually... or ship the product with famous ones already
    public ImageIcon icon = new ImageIcon("data/default/default.jpg"); // if icon not found... will only happen for crypto

    public String country, industry, weburl, marketcap, ipo;

    public JsonObject info;

    public boolean other_info_flag = true; // will be false if fetching other info was unsuccessful...

    // points to a class of price data and news data, they will be created on the constructor of the class
    // data will be parsed and stored in a way that can be processed later
    public String[] price_data;
    public String[] news_data;

    public Float[][] historical_data;

    AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();
    FinnhubAPI FinnhubAPIHandler = new FinnhubAPI();
    HistoricalData HistoricalDataGetter = new HistoricalData();



    // instantiate the stock with getting useful data automatically
    public Asset(String ticker) throws Exception {
        // using two sources to get the relevant data about the stock...
        JsonObject response = AlpacaAPIHandler.ticker_info(ticker).get(0).getAsJsonObject();

        info = response;

        // info about the stock
        try {
            this.ticker = info.get("symbol").getAsString();
            this.name = info.get("name").getAsString();
            this.exchange = info.get("exchange").getAsString();
            this.type = info.get("class").getAsString();


            // checking if local file for icon exists // TODO: add this to criterion for caching complexity
            File local_icon = new File("data/stock/" + ticker + "/" + ticker + ".png");
            if (local_icon.exists()) {
                this.icon = new ImageIcon("data/stock/" + ticker + "/" + ticker + ".png"); // setting the icon to the local file if exists
            }

            // Trying to get other info from finnhub... won't work for cryptos...
            // we only want to run this if it's not a crypto // TODO: make it neater by perhaps adding it to a different function

            if (!type.equals("crypto")) { // speeds us the program as doesn't need to do unnecessary requests...
                try {

                    JsonObject other_data = FinnhubAPIHandler.company_profile(ticker).get(0).getAsJsonObject();

                    this.country = other_data.get("country").getAsString();
                    this.industry = other_data.get("finnhubIndustry").getAsString();
                    this.marketcap = other_data.get("marketCapitalization").getAsString();
                    this.ipo = other_data.get("ipo").getAsString();
                    this.weburl = other_data.get("weburl").getAsString();


                    // checking if local file for icon exists // TODO: add this to criterion for caching complexity
                    if (local_icon.exists() == false) { // if file doesn't exists// setting the icon to the local file if exists

                        String url = other_data.get("logo").getAsString();
                        try (InputStream in = new URL(url).openStream()) {
                            Files.copy(in, Paths.get("data/stock/" + ticker + "/" + ticker + ".png"));
                        }

                        this.icon = new ImageIcon("data/stock/" + ticker + "/" + ticker + ".png"); // setting the icon to the local file if exists

                    }

                } catch (Exception e) {
                    System.out.println("Stock not found in Finnhub...");
                    other_info_flag = false; // flagging it that no info was found, helps other methods...
                }
            }



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

    public static Asset create(String ticker) throws Exception {
        String type = type(ticker);
        System.out.println(type);
        switch (type) {
            case "us_equity":
                return new Stock(ticker);
            case "crypto":
                return new Crypto(ticker);
            default:
                System.out.println("default");
                // TODO: try forex, else say doesn't exists... throw an exception if the stock doesn't exists
                return new Forex(ticker);
        }

    }

    public static String type(String ticker) throws Exception { // returns if it's a stock or crypto
        AlpacaAPI AlpacaAPIHandler = new AlpacaAPI(); // to restrict it's scope
        JsonObject response = AlpacaAPIHandler.ticker_info(ticker).get(0).getAsJsonObject();
        return response.get("class").getAsString();
    }

    // gets and updates the historical data, can also be accessed directly by the shared variable
    // TODO : make a cache of the files for efficiency...
    public Float[][] getHistorical_data() throws Exception {
        this.historical_data = HistoricalDataGetter.get(ticker);
        return historical_data;
    }

    public ArrayList<Float> getHistorical_data(int row) {
        ArrayList<Float> row_prices = new ArrayList<>();

        for (int x = 0; x < historical_data.length; x++){
            row_prices.add(historical_data[x][row]);
        }
        return row_prices;
    }


    public JsonArray getNewsData() throws Exception {
        NewsData newsData = new NewsData();
        return newsData.get(ticker);
    }

    public String buy(int number){
        // TODO: Add this method to AlpacaAPI
        return "Done";
    }

    public String sell(int number){
        // TODO: Add this method to AlpacaAPI
        return "Done";
    }

    public void getPositions() throws Exception {
        JsonElement positions = AlpacaAPIHandler.positions(ticker);
        System.out.println(positions);
    }
    
    public void getTrades() throws Exception {
        JsonElement trades = AlpacaAPIHandler.stock_trades(ticker);
        System.out.println(trades);
    }

    // TODO : https://finnhub.io/docs/api/company-basic-financials | https://finnhub.io/docs/api/financials-reported
    public void getFinancials(){
    }

    // Other Data
    // https://finnhub.io/docs/api/recommendation-trends
    // https://finnhub.io/docs/api/company-earnings

    // https://finnhub.io/docs/api/indices-constituents

}
