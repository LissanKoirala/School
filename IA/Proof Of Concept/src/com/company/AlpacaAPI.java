package com.company;

import com.google.gson.JsonArray;

public class AlpacaAPI {
    private String base_url = "https://paper-api.alpaca.markets";
    RequestHandler ReqHandler = new RequestHandler();
    private String api_key_id = "PKZLW20DRB2DJFPPRMZV";
    private String api_secret_key = "HS655suik2zGGRFXbqe5b7Xc1GRT8jHFVxQxzD1g";

    public AlpacaAPI(){
    }

    // makes the final request with the headers necessary like the api keys, etc
    // This is useful as I don't have to repeat it in every method, like specifying the api keys etc...
    public JsonArray make_request(String request_url) throws Exception {
        return ReqHandler.get(request_url, "APCA-API-KEY-ID", api_key_id, "APCA-API-SECRET-KEY", api_secret_key);
    }

    public JsonArray clock() throws Exception {
        String request_url = base_url+"/v2/clock";
        // TODO : make the numbers of cookies dynamic, pass it as a array or something!
        return make_request(request_url);
    }

    public JsonArray account() throws Exception {
        String request_url = base_url+"/v2/account";
        return make_request(request_url);
    }

    // TODO: breaks string to json, has []
    public JsonArray orders() throws Exception {
        String request_url = base_url+"/v2/orders";
        return make_request(request_url);
    }

    // TODO: breaks string to json, has []
    public JsonArray positions() throws Exception {
        String request_url = base_url+"/v2/positions";
        return make_request(request_url);
    }

    public JsonArray positions(String ticker) throws Exception {
        String request_url = base_url+"/v2/positions/"+ticker;
        return make_request(request_url);
    }

    public JsonArray assets() throws Exception {
        String request_url = base_url+"/v2/assets";
        return make_request(request_url);
    }

    public JsonArray ticker_info(String ticker) throws Exception {
        String request_url = base_url+"/v2/assets/"+ticker;
        return make_request(request_url);
    }

    // TODO: add functionality, will be used for auto trading, will categories things like volatility, etc
    public JsonArray watchlists() throws Exception {
        String request_url = base_url+"/v2/watchlists";
        return make_request(request_url);
    }

    public JsonArray watchlists(String id) throws Exception {
        String request_url = base_url+"/v2/watchlists"+id;
        return make_request(request_url);
    }

    public JsonArray portfolio_history() throws Exception {
        String request_url = base_url+"/v2/account/portfolio/history";
        return make_request(request_url);
    }

    public JsonArray stock_trades(String symbol) throws Exception {
        String request_url = base_url+"/v2/stocks/"+symbol+"/trades";
        return make_request(request_url);
    }



    public JsonArray calendar() throws Exception {
        String request_url = base_url+"/v1/calendar";
        return make_request(request_url);
    }



    public JsonArray get_news() throws Exception {
        String request_url = "https://data.alpaca.markets/v1beta1/news";
        return make_request(request_url);
    }

}
