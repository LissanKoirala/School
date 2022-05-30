package com.asset;

import com.api.CoinMarketCapAPI;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Crypto extends Asset {

    private String YFticker;
    private String CMCticker;
    CoinMarketCapAPI CoinMarketCapAPIHandler = new CoinMarketCapAPI();

    public Crypto(String ticker) throws Exception {
        super(ticker);
        this.YFticker = ticker.replace("USD", "-USD"); // convention ticker for YF...
        this.CMCticker = ticker.replace("USD", "");
        getHistorical_data();


        // getting the right logo...

        // checking if local file for icon exists // TODO: add this to criterion for caching complexity
        File local_icon = new File("data/stock/" + ticker + "/" + ticker + ".png");
        if (local_icon.exists()) {
            this.icon = new ImageIcon("data/stock/" + ticker + "/" + ticker + ".png"); // setting the icon to the local file if exists
        } else {
            JsonObject response = CoinMarketCapAPIHandler.crypto_info(CMCticker).get(0).getAsJsonObject();
            JsonObject data = response.get("data").getAsJsonObject();
            data = data.get(CMCticker).getAsJsonObject();

            String url = data.get("logo").getAsString().replace("64x64","128x128"); // asking for a higher quality image...

            try (InputStream in = new URL(url).openStream()) {
                Files.copy(in, Paths.get("data/stock/" + ticker + "/" + ticker + ".png"));
            }

            this.icon = new ImageIcon("data/stock/" + ticker + "/" + ticker + ".png"); // setting the icon to the local file if exists
        }
    }

    @Override
    public Float[][] getHistorical_data() throws Exception {
        this.historical_data = HistoricalDataGetter.get(ticker, YFticker);
        return historical_data;
    }

    @Override
    public String quote() throws Exception {
        JsonObject response = AlpacaAPIHandler.quoteCrypto(ticker).get(0).getAsJsonObject();
        System.out.println(response);
        String quote = response.get("quote").getAsJsonObject().get("ap").getAsString();
        return quote;
    }
}
