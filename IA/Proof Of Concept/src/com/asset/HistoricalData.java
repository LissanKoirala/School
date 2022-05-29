package com.asset;

import com.api.YahooFinanceApi;
import com.utils.FileHandler;
import com.utils.Utils;

import java.io.File;

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

        Float[][] final_data = Utils.convertStringArrayToFloatArray(data);

        // printing the array out
        // System.out.println(Arrays.deepToString(final_data));

        return final_data;

    }

    public Float[][] get(String ticker) throws Exception {
        return get(ticker, ticker); // calling the function and passing the same parameters as if YFticker is not given, assumes it's the same
    }

    // TODO: https://finnhub.io/docs/api/stock-candles | https://finnhub.io/docs/api/crypto-candles - useful for smaller resolution, intra-day trades...

    // because data returned is different in forex, there are null parameters
    public Float[][] getForex(String ticker, String YFticker) throws Exception {
        String filename = "data/stock/" + ticker + "/" + ticker + ".csv";
        String historical_data = YFHandler.get_historical(YFticker); // gets the data
        historical_data = historical_data.replace("Date,Open,High,Low,Close,Adj Close,Volume\n", ""); // cleaning up

        fileHandler.writeToFile(filename, historical_data, false); // storing it to make it easy to process, also to save having to ask everytime

//        System.out.println(FileHandler.getRowNumber(filename)); // this many data points, could look good in console...

        String[][] data = Utils.convertToMultiDArray(filename, 7); // 7 because it stays the same, 7 things in the column

        // remove any lines with null data points -- repeat from Utils because of some changes...
        Float[][] floatArray = new Float[data.length][data[0].length]; // figuring out how big the original array was


        for (int x = 0; x < data.length; x++){
            for (int y = 0; y < data[x].length; y++){
                if (y == 0) { // if it's a date, convert to unix timestamp...
                    floatArray[x][y] = new Float(0); // TODO: change this to actual time
                } else {
                    String value = data[x][y];
                    if (value.equals("null")) {
                        floatArray[x][y] = new Float(0);
                    } else {
                        floatArray[x][y] = new Float(data[x][y]);
                    }
                }
            }
        }

        return floatArray;

    }

}
