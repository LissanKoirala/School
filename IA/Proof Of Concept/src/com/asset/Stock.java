package com.asset;

// https://companiesmarketcap.com/
public class Stock extends Asset {

    public Stock(String ticker) throws Exception {
        super(ticker);
        getHistorical_data();
    }
}
