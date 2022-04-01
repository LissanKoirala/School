package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        YahooFinanceApi YFHandler = new YahooFinanceApi();
        YFHandler.get_historical("TSLA");

    }
}
