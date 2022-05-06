package com.analyzer.backtesting;

import com.analyzer.tools.SMA;
import com.stock.Stock;

import java.util.ArrayList;
import java.util.Arrays;

public class SMACrossoverTester {
    private Stock stock;
    private Float[][] historicalData;

    public SMACrossoverTester(Stock stock){
        this.stock = stock;
        this.historicalData = stock.historical_data;
    }

    // Simulating the data to figure out the gain made... if had bought at the closing price
    // TODO: make it accept other values such as opening price or average day price...
    public float test(int sma1, int sma2) throws Exception {
        float total_gain = 0;

        SMA SMA_1 = new SMA(sma1);
        SMA SMA_2 = new SMA(sma2);

        ArrayList<Float> sma_data_1 =  SMA_1.getSMAData(stock);
        ArrayList<Float> sma_data_2 =  SMA_2.getSMAData(stock);

        System.out.println(Arrays.deepToString(sma_data_1.toArray()));
        System.out.println(Arrays.deepToString(sma_data_2.toArray()));

        int total_data_size = sma_data_1.size();



        System.out.println(sma1 + " : " + sma2);

        // creating an array with info if the sma1 was above or below sma2
        Boolean[] crossover = new Boolean[total_data_size];
        for (int i = 0; i < total_data_size; i++){
            if (sma_data_1.get(i) > sma_data_2.get(i)){
                crossover[i] = true;
            } else {
                crossover[i] = false;
            }
        }

        // checking whether it's buy or sell point if any
        Integer[] buy_sell = new Integer[total_data_size];
        for (int i = 0; i < total_data_size-1; i++){ // until the second last index as next is i+1
            boolean previous = crossover[i];
            boolean next = crossover[i+1];

            if (previous != next){
                if (previous == true && next == false){ // if small crosses the higher, sell
                    buy_sell[i] = 0; // 1 - buy & 0 - sell
                } else if (previous == false && next == true){ // if small crosses the higher, buy
                    buy_sell[i] = 1;
                } else {
                    buy_sell[i] = -1; // if the same, nothing
                }
            }
        }


        // printing the final results
        for (int i = 0; i < total_data_size; i++){
            System.out.println(sma_data_1.get(i) + " : " + sma_data_2.get(i) + " - " + crossover[i] + " -------- " + buy_sell[i]);
        }


        // Calculating the total gain made...
        // For now, just does buy, no short TODO: make it calculate for short as well...
        float last_bought = 0;
        float last_sold = 0;

        for (int i = 0; i < total_data_size; i++){

            if (buy_sell[i] != null) { // making sure the data exists as it won't for the last index due to i+1 used previously in buy_sell
                if (buy_sell[i] == 1) {
                    last_bought = historicalData[i][5]; // always 5 as it's the closing price...
                }

                if (buy_sell[i] == 0) {

                    if (last_bought != 0) { // if the stock has been bought once before...
                        last_sold = historicalData[i][5];
                        total_gain = total_gain + (last_sold / last_bought);
                    }
                }
            }
        }

        return total_gain*100;
    }
}
