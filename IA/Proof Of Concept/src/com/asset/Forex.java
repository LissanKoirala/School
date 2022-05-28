package com.asset;

public class Forex extends Asset {

    private String YFticker;

    // TODO: I don't think I wanna inherit this as the similarity are very limited and causes errors for normal api calls

    public Forex(String ticker) throws Exception {
        super(ticker);
        this.ticker = ticker;
        this.YFticker = ticker + "=X"; // separate ticker convention for YF...
        getHistorical_data();
    }

    @Override
    public Float[][] getHistorical_data() throws Exception {
        this.historical_data = HistoricalDataGetter.get(ticker, YFticker);
        return historical_data;
    }
}
