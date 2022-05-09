package com.company;

import com.analyzer.Analyze;
import com.analyzer.backtesting.SMACrossoverTester;
import com.analyzer.strategies.SMACrossover;
import com.api.AlpacaAPI;
import com.api.YahooFinanceApi;
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

//        GUICaller GUICaller = new GUICaller();
//        GUICaller.Login();

//        for(int i = 0; i < 5; i++) {
//            MultiThreadRunner multiThreadRunner = new MultiThreadRunner();
//            multiThreadRunner.start();
//        }

        Stock stock = new Stock("TSLA");
//        System.out.println(stock.name);
//        System.out.println(stock.getNewsData());

        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(stock);

        // run a simulation to figure out which one smas would have done the best for this stock...
//        System.out.println("\n\nSMA1 : 50 & SMA2 : 180 - " + Arrays.toString(smaCrossoverTester.test(61, 39)));

        System.out.println("\n\nRunning simulation to figure out the best SMAs");
//        smaCrossoverTester.simulate();

        // Simulating for a array of stocks...
        String[] stocks = {"AAPL","MSFT","AMZN","TSLA","GOOG","BRK.B","FB","NVDA","JPM","V","MA","PFE","BAC","KO","DIS","MCD","INTC","NFLX","BLK","BA","TDOC"};
        for (String s: stocks){
            stock = new Stock(s);
            SMACrossoverTester smaCrossoverTester1 = new SMACrossoverTester(stock);

            new Thread(() -> {
                try {
                    smaCrossoverTester1.simulate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
