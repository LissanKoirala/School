package com.asset;

import com.api.AlpacaAPI;
import com.api.RequestHandler;
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
    public String about = null;
    public JsonObject info;
    File local_icon;

    // points to a class of price data and news data, they will be created on the constructor of the class
    // data will be parsed and stored in a way that can be processed later
    public String[] price_data;
    public String[] news_data;

    public Float[][] historical_data;

    AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();
    HistoricalData HistoricalDataGetter = new HistoricalData();
    RequestHandler RequestHandler = new RequestHandler();


    // instantiate the stock with getting useful data automatically
    public Asset(String ticker) throws Exception {

        // Creating directory to store asset details
        File directory = new File("data/stock/" + ticker);
        if (! directory.exists()){
            directory.mkdirs();
        }

        // info about the asset, TODO: add to criterion, will work for stocks and cryptos, use of inheritance
        try {

            // using sources to get the relevant data about the stock...

            info = AlpacaAPIHandler.ticker_info(ticker).get(0).getAsJsonObject();

            this.ticker = info.get("symbol").getAsString();
            this.name = info.get("name").getAsString();
            this.exchange = info.get("exchange").getAsString();
            this.type = info.get("class").getAsString();

            // TODO: Try using this, this gets the info about the company...
            // https://lissanchatbot.herokuapp.com/get?msg=ticker
            // TODO: works but taking long to load
            try {
                // TODO: See if the response is actually about the asset
                String response = RequestHandler.getString("https://lissanchatbot.herokuapp.com/get?msg="+ticker);
                if(!response.equals("Sorry, I cannot think of a reply for that!")){
                    this.about = response;
                }
            } catch (Exception e){
                System.out.println(e);
            }



            // checking if local file for icon exists // TODO: add this to criterion for caching complexity
            local_icon = new File("data/stock/" + ticker + "/" + ticker + ".png");
            if (local_icon.exists()) {
                this.icon = new ImageIcon("data/stock/" + ticker + "/" + ticker + ".png"); // setting the icon to the local file if exists
            }


        } catch (Exception e){ // if stock doesn't exists
            System.out.println("Stock Not Found in Alpaca");
            System.out.println(e);

            this.ticker = ticker;
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
        try {
            AlpacaAPI AlpacaAPIHandler = new AlpacaAPI(); // to restrict it's scope
            JsonObject response = AlpacaAPIHandler.ticker_info(ticker).get(0).getAsJsonObject();
            return response.get("class").getAsString();
        } catch (Exception e){
            System.out.println("Asset not found");
            System.out.println(e);
            return "null"; // not found
        }
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

    public String quote() throws Exception {
        JsonObject response = AlpacaAPIHandler.quoteStock(ticker).get(0).getAsJsonObject();
        String quote = response.get("quote").getAsJsonObject().get("ap").getAsString();
        return quote;
    }

    public String price() throws Exception {
        JsonObject response = AlpacaAPIHandler.latestTradeStock(ticker).get(0).getAsJsonObject();
        System.out.println(response);
        String quote = response.get("trade").getAsJsonObject().get("p").getAsString();
        return quote;
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
