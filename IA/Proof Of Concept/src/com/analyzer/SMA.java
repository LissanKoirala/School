package com.analyzer;

// Source : https://www.geeksforgeeks.org/program-find-simple-moving-average/

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

}
