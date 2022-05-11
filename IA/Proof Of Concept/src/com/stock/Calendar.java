package com.stock;

import com.api.AlpacaAPI;

public class Calendar {
    AlpacaAPI AlpacaAPIHandler = new AlpacaAPI();
    public Calendar(){
    }

    public void isMarketOpen() throws Exception {
        AlpacaAPIHandler.clock();
    }
}

// TODO: https://finnhub.io/docs/api/earnings-calendar