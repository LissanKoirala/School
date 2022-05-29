package com.user;

import com.asset.Asset;

import java.util.ArrayList;

// TODO: also sync up the watchlist on cloud...
public class Watchlist {

    ArrayList<String> watchlist = new ArrayList<>();

    // TODO: instantiate this class by reading in a file or just setting it to default
    public Watchlist(String name){
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
}
