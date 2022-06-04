package com.user;

import com.asset.Asset;

import java.util.ArrayList;

// TODO: also sync up the watchlist on cloud...
public class Watchlist {

    ArrayList<String> watchlist = new ArrayList<>();

    // TODO: instantiate this class by reading in a file or just setting it to default
    public Watchlist(String name){
        // TODO: these tickers need to be yf compatible somehow... to call the correlation function etc...
        // TODO: instead of saving the ticker as string, save them as a ArrayList of class etc...
        addTicker("AAPL");
        addTicker("TSLA");
        addTicker("BTCUSD");
        addTicker("GOOG");
        addTicker("GBPUSD");
    }

    public void addTicker(String ticker){
        watchlist.add(ticker);
    }

    public void remove(String ticker){
        for (String tckr: watchlist){
            if(ticker.equals(tckr)){
                watchlist.remove(tckr);
            }
        }
    }

    public String[] get(){
        String[] arraywatchlist = new String[watchlist.size()];
        for (int i = 0; i<watchlist.size(); i++){
            arraywatchlist[i] = watchlist.get(i);
        }

        return arraywatchlist;
    }

    public String getAsString(){
        String tickers = "";
        for (String s : watchlist) {
            tickers += s + ",";
        }
        return tickers.substring(0, tickers.length() - 1); // removing the last character // https://www.baeldung.com/java-remove-last-character-of-string
    }
}
