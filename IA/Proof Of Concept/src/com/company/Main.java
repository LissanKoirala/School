package com.company;

import com.analyzer.Analyze;
import com.analyzer.backtesting.SMACrossoverTester;
import com.analyzer.strategies.SMACrossover;
import com.api.AlpacaAPI;
import com.api.FinnhubAPI;
import com.api.YahooFinanceApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gui.GUICaller;
import com.stock.HistoricalData;
import com.stock.Stock;
import com.utils.FileHandler;
import com.utils.SentimentAnalysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

//        YahooFinanceApi YFHandler = new YahooFinanceApi();
//        String historical_data = YFHandler.get_historical("DOGE-USD");
//        System.out.println(historical_data);
//
//        FileHandler FileHandler = new FileHandler();
//        FileHandler.writeToFile("TSLA.csv", historical_data, false);


        AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();
        FinnhubAPI FinnhubAPIHandler = new FinnhubAPI();
//        System.out.println(AlpacaAPIHandler.clock());
//        System.out.println(AlpacaAPIHandler.account());
//        System.out.println(AlpacaAPIHandler.orders());
//        System.out.println(AlpacaAPIHandler.positions());
//        System.out.println(AlpacaAPIHandler.watchlists());
//
//
//        // Gets all the assets
//        //System.out.println(AlpacaAPIHandler.assets());
        System.out.println(AlpacaAPIHandler.ticker_info("TSLA"));
        System.out.println(FinnhubAPIHandler.company_profile("TSLA"));


//
//        System.out.println(AlpacaAPIHandler.portfolio_history());
//        //System.out.println(AlpacaAPIHandler.stock_trades("TSLA"));
//        System.out.println(AlpacaAPIHandler.get_news());
//        System.out.println(AlpacaAPIHandler.get_news("DOGEUSD"));
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

//        Stock stock = new Stock("TSLA");
//        System.out.println(stock.info);
//        System.out.println(stock.getNewsData());

//        System.out.println(Arrays.deepToString(stock.historical_data));

//        Analyze Analyzer = new Analyze();

//        SMACrossover smaCrossover = new SMACrossover();
//        System.out.println(smaCrossover.check(stock, 50, 180)); // returns true or false, over or under.

        GUICaller GUICaller = new GUICaller();
//        GUICaller.Login();
        GUICaller.StockChooser();

//        for(int i = 0; i < 5; i++) {
//            MultiThreadRunner multiThreadRunner = new MultiThreadRunner();
//            multiThreadRunner.start();
//        }

        Stock stock = new Stock("AAPL");
//        System.out.println(stock.name);
//        System.out.println(stock.getNewsData());

//        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(stock);

        // run a simulation to figure out which one smas would have done the best for this stock...
//        System.out.println("\n\nSMA1 : 61 & SMA2 : 39 - " + Arrays.toString(smaCrossoverTester.test(51, 39, true)));

//        System.out.println("\n\nRunning simulation to figure out the best SMAs");
//        smaCrossoverTester.simulate();

        // Simulating for a array of stocks...
//        String[] stocks = {"AAPL","MSFT","AMZN","TSLA","GOOG","BRK.B","FB","NVDA","JPM","V","MA","BAC","DIS","MCD","NFLX","BLK","BA","BTCUSD","ETHUSD"};
//        for (String s: stocks){
//            stock = new Stock(s);
//            SMACrossoverTester smaCrossoverTester1 = new SMACrossoverTester(stock);
//
//            new Thread(() -> {
//                try {
//                    smaCrossoverTester1.simulate();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }

        // TODO: Add dates to the trades log... [for backtesting log]
    }
}
