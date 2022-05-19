package com.analyzer.backtesting;

import com.analyzer.tools.SMA;
import com.stock.Asset;
import com.utils.FileHandler;

import java.util.ArrayList;

// TODO: Might wanna log the calculations to a csv file so that it can be processed in exel if wanted to
// TODO: save the things to be logged to a arraylist then pass it to a thread to save to a file...
public class SMACrossoverTester {
    private Asset asset;
    private Float[][] historicalData;
    private FileHandler fileHandler = new FileHandler();

    String ticker;


    public SMACrossoverTester(Asset asset) throws Exception {
        this.asset = asset;
        this.ticker = asset.ticker;
        this.historicalData = asset.getHistorical_data();
    }

    // Simulating the data to figure out the gain made... if had bought at the closing price
    // TODO: make it accept other values such as opening price or average day price...
    // returns the total_gain with number of trades made...
    public float[] test(int sma1, int sma2, boolean log_trades) throws Exception {
        float total_gain = 0;
        int numbers_of_trades = 0;

        StringBuilder buy_sell_log = new StringBuilder();
        String log = "";


        SMA SMA_1 = new SMA(sma1);
        SMA SMA_2 = new SMA(sma2);

        ArrayList<Float> sma_data_1 =  SMA_1.getSMAData(asset);
        ArrayList<Float> sma_data_2 =  SMA_2.getSMAData(asset);

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
                if (previous == true & next == false){ // if small crosses the higher, sell
                    buy_sell[i] = 0; // 1 - buy & 0 - sell
                    numbers_of_trades += 1;

                } else if (previous == false & next == true){ // if small crosses the higher, buy
                    buy_sell[i] = 1;
                    numbers_of_trades += 1;

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
                    if (log_trades) {
                        log = "buy,"+last_bought+"\n";
                        buy_sell_log.append(log);
                    }
                }

                if (buy_sell[i] == 0) {

                    if (last_bought != 0) { // if the stock has been bought once before...
                        // TODO: When would you sell? At the next day's open or at that day's close? - Make the decision
                        last_sold = (historicalData[i][5] + historicalData[i][1])/2; // average price of the day...
                        float gain = (last_sold-last_bought)/last_bought;
                        total_gain = total_gain + gain;
                        if (log_trades) {
                            log = "sell," + last_sold + "," + gain*100 + "\n";
                            buy_sell_log.append(log);
                        }
                    }
                }
            }
        }

        if (log_trades) {
            fileHandler.writeToFile("data/stock/"+ticker+"/best-sma-crossover-simulation-trades.csv", String.valueOf(buy_sell_log),false);
        }

        return new float[]{(total_gain * 100), numbers_of_trades};
    }

    public int[] simulate() throws Exception {
        int number_of_trades = 0;
        float[] result;

        float highest_returns = 0;
        int bestSMA1 = 0;
        int bestSMA2 = 0;
        int number_of_trades_best = 0; // number of trades for the best smas

        StringBuilder simulation_log = new StringBuilder();


        // starting with the lowest sma of 5 as lower numbers produce insanely high uncertainty
        // TODO: Figure out best best lower sma to start at
        for (int sma1 = 20; sma1<201; sma1++){
            for (int sma2 = 20; sma2<201; sma2++){
                // TODO: if sma1 is bigger than sma2, this means that shorting is going one instead of buying, make this clear

                result = test(sma1, sma2, false);
                float gain = result[0];
                number_of_trades = (int) result[1];

                // Comment this out to not log... TODO: Add a section in Preferences...
                String log = sma1 + "," + sma2 + "," + gain + "," + number_of_trades+"\n";
                simulation_log.append(log); // TODO: Include in Criterion - FAR FAR FAR more effieicne then just String += log; less time and processing power

                if (gain > highest_returns) {
                    highest_returns = gain;
                    bestSMA1 = sma1;
                    bestSMA2 = sma2;
                    number_of_trades_best = number_of_trades;
                }

            }
        }   
        
        // TODO: This doesn't work currently, update it to the lastest trade it mades, currently if it does long,long,short, it says it shorted, ignoring the rest
        String position_type = "long";
        if(bestSMA1>bestSMA2){position_type = "short";}

//        System.out.println(stock.name + " | Best SMA1 : " + bestSMA1 + " & SMA2 : " + bestSMA2 + " | Returns : " + highest_returns + " | No. of Trades : " + number_of_trades + " | Type : " + position_type);
        String final_result = "SMA-Crossover," + ticker + "," + bestSMA1 + "," + bestSMA2 + "," + highest_returns + "," + number_of_trades_best + "," + position_type;
        System.out.println(final_result);


        // Logging to a file...
        test(bestSMA1,bestSMA2,true); // making it log the trades...
        fileHandler.writeToFile("data/stock/"+ticker+"/simulation-sma.csv", simulation_log.toString(),false);
        fileHandler.writeToFile("data/simulation-result.csv",final_result,true); // adding all to a since file // TODO: remove duplicates

        // TODO: Want to return the highest returns as float? 30.73 % seems realistic and believable than 30 %
        return new int[]{bestSMA1,bestSMA2, (int) highest_returns, number_of_trades};
    }
}
