package com.company;

public class YahooFinanceApi {

    public YahooFinanceApi(){
    }

    // not really a api call but still acts like it...
    public void get_historical(String ticker){
        String url = "https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%d&period2=%d&interval=%s&events=history&includeAdjustedClose=true";
        String request_url = String.format(url, ticker, 1269907200, 1648598400, "1d");
        // now make the call
        System.out.println(request_url);
    }
}
