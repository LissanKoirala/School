package com.stock;

import com.api.YahooFinanceApi;
import com.utils.FileHandler;
import com.utils.Utils;
import java.util.Arrays;

public class HistoricalData {
    private YahooFinanceApi YFHandler = new YahooFinanceApi();
    private FileHandler FileHandler = new FileHandler();
    private Utils Utils = new Utils();


    public HistoricalData(){
    }

    public Float[][] get(String ticker) throws Exception {
        String filename = ticker + ".csv";
        String historical_data = YFHandler.get_historical(ticker); // gets the data
        historical_data = historical_data.replace("Date,Open,High,Low,Close,Adj Close,Volume\n",""); // cleaning up
        FileHandler.writeToFile(filename, historical_data, false); // storing it to make it easy to process, also to save having to ask everytime


        System.out.println(FileHandler.getRowNumber(filename));
        String[][] data = Utils.convertToMultiDArray(filename, 7); // 7 because it stays the same, 7 things in the column

        Float[][] final_data = Utils.convertStringArrayToFloatArray(data);

        // printing the array out
        System.out.println(Arrays.deepToString(final_data));

        return final_data;

    }


}
