package com.analyzer.tools;

// Source : https://www.geeksforgeeks.org/program-find-simple-moving-average/

import com.stock.Stock;

import java.util.*;

public class SMA {

    // queue used to store list so that we get the average
    private Queue<Double> Dataset = new LinkedList<Double>();
    private int period;
    private double sum;

    // constructor to initialize period
    public SMA(int period) {
        this.period = period;
    }

    // function to add new data in the
    // list and update the sum so that
    // we get the new mean
    public void addData(double num)
    {
        sum += num;
        Dataset.add(num);

        // Updating size so that length
        // of data set should be equal
        // to period as a normal mean has
        if (Dataset.size() > period)
        {
            sum -= Dataset.remove();
        }
    }

    // function to calculate mean
    public double getMean() {
        return sum / period;
    }

    // returns the SMA data over the period of historical data give, can be plotted too...
    public ArrayList<Float> getSMAData(Stock stock) throws Exception {
        ArrayList<Float> sma = new ArrayList<>(); // array with the SMA prices accordingly

        ArrayList<Float> total = new ArrayList<>(); // array with all the prices
        Float[][] historical_data = stock.historical_data;

        for(int x = 0; x < historical_data.length; x++){
            total.add(historical_data[x][5]); // takes the avg close price
        }

        SMA obj = new SMA(period);
        for (double x : total) {
            obj.addData(x);
            sma.add((float) obj.getMean()); // making the return type float
        }

        return sma;

    }
}
