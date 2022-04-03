package com.company;

public class Stock {
    public String ticker;
    public String name;
    // points to a class of price data and news data, they will be created on the constructor of the class
    // data will be parsed and stored in a way that can be processed later
    public String[] price_data;
    public String[] news_data;

    public Stock(String ticker){
        this.ticker = ticker;
    }

    public Stock(String ticker, String name){
        this.ticker = ticker;
        this.name = name;

    }
}
