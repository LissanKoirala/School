package com.asset;

import com.api.YahooFinanceApi;
import com.utils.FileHandler;
import com.utils.Utils;

import java.io.File;
import java.util.Arrays;

public class HistoricalData {
    private YahooFinanceApi YFHandler = new YahooFinanceApi();
    private FileHandler fileHandler = new FileHandler();
    private Utils Utils = new Utils();


    public HistoricalData(){
    }


    public Float[][] get(String ticker, String YFticker) throws Exception {

        String filename = "data/stock/" + ticker + "/" + ticker + ".csv";
        String historical_data = YFHandler.get_historical(YFticker); // gets the data
        historical_data = historical_data.replace("Date,Open,High,Low,Close,Adj Close,Volume\n", ""); // cleaning up

        fileHandler.writeToFile(filename, historical_data, false); // storing it to make it easy to process, also to save having to ask everytime

//        System.out.println(FileHandler.getRowNumber(filename)); // this many data points, could look good in console...

        String[][] data = Utils.convertToMultiDArray(filename, 7); // 7 because it stays the same, 7 things in the column

        Float[][] final_data;

        try {
            final_data = Utils.convertStringArrayToFloatArray(data);

        } catch (Exception e){ // catching points where this might be null in the data, ONLY executes if catches null value errors
            System.out.println("Null value found in the historical data");
            System.out.println(e);

            // remove any lines with null data points -- repeat from Utils because of some changes...
            final_data = new Float[data.length][data[0].length]; // figuring out how big the original array was

            // can't make it zero... // TODO: Include this in Criterion.
            // this to replace any null values for close data, as it's the closet to replacing the missing values
            float[] previous_day_data = new float[data[0].length];

            for (int x = 0; x < data.length; x++){
                for (int y = 0; y < data[x].length; y++){

                    if (y == 0) { // if it's a date, convert to unix timestamp...
                        final_data[x][y] = Float.parseFloat("0"); // TODO: change this to actual time
                    } else {
                        String value = data[x][y];
                        if (value.equals("null")) { // replacing null with previous day's data, this is a temporary approach as that data will eventually be replaced by the data provider
                            // substituting the value with previous day data
                            final_data[x][y] = previous_day_data[y];
                        }

                        else {
                            final_data[x][y] = Float.parseFloat(data[x][y]);
                            // updating previous day data
                            previous_day_data[y] = final_data[x][y];

                        }
                    }
                }
            }
        }

        // printing the array out
        // System.out.println(Arrays.deepToString(final_data));

        return final_data;

    }

    public Float[][] get(String ticker) throws Exception {
        return get(ticker, ticker); // calling the function and passing the same parameters as if YFticker is not given, assumes it's the same
    }

    // TODO: https://finnhub.io/docs/api/stock-candles | https://finnhub.io/docs/api/crypto-candles - useful for smaller resolution, intra-day trades...


}
