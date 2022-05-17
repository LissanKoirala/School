package com.trader;

import com.analyzer.backtesting.SMACrossoverTester;
import com.analyzer.strategies.SMACrossover;
import com.stock.Asset;

public class Trader {
    String type; // probably having different types helps as there might be some changes to the parameters in the algo
    String[] watchlist;

    public Trader(String type, String[] watchlists){
        this.type = type;
        this.watchlist = watchlists;
    }

    // it simply checks for crossovers for now
    public void startSMACrossOver() throws Exception {
        SMACrossover smaCrossover = new SMACrossover();
        for(String ticker: watchlist){
            Asset asset = new Asset(ticker);
            SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(asset);
            int[] result = smaCrossoverTester.simulate();
            int sma1 = result[0];
            int sma2 = result[1];

            new Thread(() -> {
                try {
                    boolean crossover = smaCrossover.check(asset, sma1, sma2); // TODO: this currently doesn't do the crossover
                    // TODO: if a crossover, buy or sell...
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
