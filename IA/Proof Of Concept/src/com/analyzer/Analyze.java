package com.analyzer;

// analyzes using all the relevant and makes a final decision

import com.stock.HistoricalData;
import com.stock.Stock;
import com.utils.Math;
import com.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Analyze {
    private Math Math = new Math();
    public Analyze(){
    }

    public ArrayList<Float> sma(Stock stock, int days) throws Exception {
        ArrayList<Float> sma = new ArrayList<>(); // array with the SMA prices accordingly
        ArrayList<Float> total = new ArrayList<>(); // array with all the prices
        Float[][] historical_data = stock.historical_data;

        for(int x = 0; x < historical_data.length; x++){
            total.add(historical_data[x][5]); // takes the avg close price
        }

        SMA obj = new SMA(days);
        for (double x : total) {
            obj.addData(x);
            sma.add((float) obj.getMean()); // making the return type float
        }


        return sma;

    }

}
