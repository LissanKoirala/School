package com.company;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

//        YahooFinanceApi YFHandler = new YahooFinanceApi();
//        String historical_data = YFHandler.get_historical("TSLA");
//        System.out.println(historical_data);
//
//        AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();
//        System.out.println(AlpacaAPIHandler.clock());
//        System.out.println(AlpacaAPIHandler.account());
//        System.out.println(AlpacaAPIHandler.orders());
//        System.out.println(AlpacaAPIHandler.positions());
//        System.out.println(AlpacaAPIHandler.watchlists());
//
//
//        // Gets all the assets
//        //System.out.println(AlpacaAPIHandler.assets());
//        System.out.println(AlpacaAPIHandler.ticker_info("TSLA"));
//
//        System.out.println(AlpacaAPIHandler.portfolio_history());
//        //System.out.println(AlpacaAPIHandler.stock_trades("TSLA"));
//        System.out.println(AlpacaAPIHandler.get_news());
//
//        Math Math = new Math();
//        ArrayList<Integer> numbs = new ArrayList<>();
//
//        for(int i = 0; i<1000; i++){
//            numbs.add(i);
//        }
//
//        float average = Math.average(numbs);
//        System.out.println(average);

        Stock stock = new Stock("TSLA");



    }
}
