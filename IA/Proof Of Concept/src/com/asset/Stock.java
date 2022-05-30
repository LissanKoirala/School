package com.asset;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

// https://companiesmarketcap.com/
public class Stock extends Asset {

    public Stock(String ticker) throws Exception {
        super(ticker);
        getHistorical_data();
    }
}
