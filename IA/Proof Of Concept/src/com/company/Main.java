package com.company;

import com.api.AlpacaAPI;
import com.api.FinnhubAPI;
import com.gui.GUICaller;
import com.asset.Asset;

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
//        System.out.println(AlpacaAPIHandler.ticker_info("TSLA"));
//        System.out.println(FinnhubAPIHandler.company_profile("TSLA"));


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

        Asset stock = Asset.create("TSLA");
//        System.out.println(stock.info);
//        System.out.println(stock.getNewsData());

//        System.out.println(Arrays.deepToString(stock.historical_data));

//        Analyze Analyzer = new Analyze();

//        SMACrossover smaCrossover = new SMACrossover();
//        System.out.println(smaCrossover.check(stock, 50, 180)); // returns true or false, over or under.

        GUICaller GUICaller = new GUICaller();
//        GUICaller.Login();
//        GUICaller.startup();
        GUICaller.Simulate(stock);


//        for(int i = 0; i < 5; i++) {
//            MultiThreadRunner multiThreadRunner = new MultiThreadRunner();
//            multiThreadRunner.start();
//        }

//        System.out.println(AlpacaAPIHandler.ticker_info("TSLA"));

//        Stock stock = new Stock("AAPL");
//        Crypto crypto = new Crypto("ETHUSD");
//        Forex forex = new Forex("GBPUSD");

//        GUICaller.Simulate(crypto);


//        System.out.println(stock.name);
//        System.out.println(stock.getNewsData());

//        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(forex);

        // run a simulation to figure out which one smas would have done the best for this stock...
//        System.out.println("\n\nSMA1 : 61 & SMA2 : 39 - " + Arrays.toString(smaCrossoverTester.test(51, 39, true)));

//        System.out.println("\n\nRunning simulation to figure out the best SMAs");
//        smaCrossoverTester.simulate();

        // Simulating for a array of stocks...
//        String[] stocks = {"BTCUSD","ETHUSD","AAPL","MSFT","AMZN","TSLA","GOOG","FB","NVDA","JPM","V","MA","BAC","DIS","MCD","NFLX","BLK","BA"};
//        for (String s: stocks){
//            Asset asset = Asset.create(s); // gets the proper class for the asset, either stock, crypto or forex...
//            SMACrossoverTester smaCrossoverTester1 = new SMACrossoverTester(asset);
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
