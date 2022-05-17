package com.stock;

public class Stock extends Asset {

    public Stock(String ticker) throws Exception {
        super(ticker);
        getHistorical_data();
    }
}
