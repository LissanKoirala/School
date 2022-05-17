package com.analyzer.strategies;

import com.analyzer.tools.SMA;
import com.stock.Asset;

import java.util.ArrayList;

public class SMACrossover {
    public SMACrossover(){
    }

    // checks if the stock's sma1 is over or under sma2, returns true or false
    // TODO: This function doesn't actually check for crossovers yet, make it do so
    public boolean check(Asset asset, int sma1, int sma2) throws Exception {
        SMA SMA_1 = new SMA(sma1);
        ArrayList<Float> sma_data =  SMA_1.getSMAData(asset);
        Float sma_1 = sma_data.get(sma_data.size()-1); // gets the last index of the array which is the latest

        SMA SMA_2 = new SMA(sma2);
        ArrayList<Float> sma_data_2 = SMA_2.getSMAData(asset);
        Float sma_2 = sma_data_2.get(sma_data_2.size()-1); // gets the last index of the array which is the latest

        System.out.println(sma_1);
        System.out.println(sma_2);

        return sma_1 > sma_2; // true or false
    }
}
