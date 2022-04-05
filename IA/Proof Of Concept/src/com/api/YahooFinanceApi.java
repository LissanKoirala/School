package com.api;

public class YahooFinanceApi {
    private String historical_data_url = "https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%d&period2=%d&interval=%s&events=history&includeAdjustedClose=true";
    RequestHandler ReqHandler = new RequestHandler();

    public YahooFinanceApi(){
    }

    // not really an stock.com.api call but still acts like it...
    // Constructs an url to request
    public String get_historical(String ticker) throws Exception {
        String request_url = String.format(historical_data_url, ticker, 1269907200, 1648598400, "1d"); // url, ticker, start time, end time, time interval eg: 1d, 1M
        return ReqHandler.getString(request_url);
    }

    public String get_historical(String ticker, int start, int end, String duration) throws Exception {
        String request_url = String.format(historical_data_url, ticker, start, end, duration); // url, ticker, start time, end time, time interval eg: 1d, 1M
        return ReqHandler.getString(request_url);
    }

}
