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
//        String historical_data = YFHandler.get_historical("TSLA");
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
//        System.out.println(AlpacaAPIHandler.get_news("TWTR"));
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
        System.out.println(stock.getNewsData());
        //System.out.println(Arrays.deepToString(stock.historical_data));

//        Analyze Analyzer = new Analyze();

//        SMACrossover smaCrossover = new SMACrossover();
//        System.out.println(smaCrossover.check(stock, 50, 180)); // returns true or false, over or under.

//        GUICaller GUICaller = new GUICaller();
//        GUICaller.Login();

        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(stock);

        // run a simulation to figure out which one smas would have done the best for this stock...
        System.out.println(smaCrossoverTester.test(50, 180));

    }
}
