package com.analyzer.backtesting;

import com.analyzer.tools.SMA;
import com.stock.Stock;

import java.util.ArrayList;

// TODO: Might wanna log the calculations to a csv file so that it can be processed in exel if wanted to
// TODO: save the things to be logged to a arraylist then pass it to a thread to save to a file...
public class SMACrossoverTester {
    private Stock stock;
    private Float[][] historicalData;


    public SMACrossoverTester(Stock stock) throws Exception {
        this.stock = stock;
        this.historicalData = stock.getHistorical_data();
    }

    // Simulating the data to figure out the gain made... if had bought at the closing price
    // TODO: make it accept other values such as opening price or average day price...
    // returns the total_gain with number of trades made...
    public float[] test(int sma1, int sma2) throws Exception {
        float total_gain = 0;
        float numbers_of_trades = 0;

        SMA SMA_1 = new SMA(sma1);
        SMA SMA_2 = new SMA(sma2);

        ArrayList<Float> sma_data_1 =  SMA_1.getSMAData(stock);
        ArrayList<Float> sma_data_2 =  SMA_2.getSMAData(stock);

//        System.out.println(Arrays.deepToString(sma_data_1.toArray()));
//        System.out.println(Arrays.deepToString(sma_data_2.toArray()));

        int total_data_size = sma_data_1.size();

//        System.out.println(sma1 + " : " + sma2);

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
                    numbers_of_trades += 1.0;

                } else if (previous == false && next == true){ // if small crosses the higher, buy
                    buy_sell[i] = 1;
                    numbers_of_trades += 1.0;

                } else {
                    buy_sell[i] = -1; // if the same, nothing
                }
            }
        }


//        // printing the final results
//        for (int i = 0; i < total_data_size; i++){
//            System.out.println(sma_data_1.get(i) + " : " + sma_data_2.get(i) + " - " + crossover[i] + " -------- " + buy_sell[i]);
//        }


        // Calculating the total gain made...
        // For now, just does buy, no short TODO: make it calculate for short as well...
        float last_bought = 0;
        float last_sold = 0;

        for (int i = 0; i < total_data_size; i++){

            if (buy_sell[i] != null) { // making sure the data exists as it won't for the last index due to i+1 used previously in buy_sell
                if (buy_sell[i] == 1) {
                    last_bought = (historicalData[i][5] + historicalData[i][1])/2; // average price of the day... 5 - close, 1 - open
                }

                if (buy_sell[i] == 0) {

                    if (last_bought != 0) { // if the stock has been bought once before...
                        last_sold = historicalData[i][5];
                        total_gain = total_gain + (last_sold-last_bought)/last_bought;
                    }
                }
            }
        }

//        System.out.println(numbers_of_trades);

        return new float[]{(total_gain * 100), numbers_of_trades};
    }

    public int[] simulate() throws Exception {
        int number_of_trades = 0;
        float[] result;
        float highest_returns = 0;
        int bestSMA1 = 0;
        int bestSMA2 = 0;

        // starting with the lowest sma of 5 as lower numbers produce insanely high uncertainty
        // TODO: Figure out best best lower sma to start at
        for (int sma1 = 20; sma1<201; sma1++){
            for (int sma2 = 20; sma2<201; sma2++){
                // TODO: if sma1 is bigger than sma2, this means that shorting is going one instead of buying, make this clear

                result = test(sma1, sma2);
                float gain = result[0];

                // TODO: this is the calculation for each smas, add it to a csv through a thread
//                System.out.println(stock.ticker + "," + sma1 + "," + sma2 + "," + gain + "," + number_of_trades);

                if (gain > highest_returns) {
                    highest_returns = gain;
                    bestSMA1 = sma1;
                    bestSMA2 = sma2;
                    number_of_trades = (int) result[1];
                }

            }
        }

        String position_type = "long";
        if(bestSMA1>bestSMA2){position_type = "short";}

//        System.out.println(stock.name + " | Best SMA1 : " + bestSMA1 + " & SMA2 : " + bestSMA2 + " | Returns : " + highest_returns + " | No. of Trades : " + number_of_trades + " | Type : " + position_type);
        System.out.println(stock.ticker + "," + bestSMA1 + "," + bestSMA2 + "," + highest_returns + "," + number_of_trades + "," + position_type);

        return new int[]{bestSMA1,bestSMA2};
    }
}