package com.analyzer.tools;

import java.io.IOException;

// TODO: plot the percentage increases of multiple graphs, let the user see how it correlates
// TODO: For the future, make it calculate the correlation coefficient, just in default mode and also a mode where it simulates and finds out the best one, like automatically finding out the days offset
public class Correlation {
    public Correlation(){
    }

    public void find(String tickers, String start_date, String end_date) throws IOException {
        Runtime rt = Runtime.getRuntime();
        // Calls the python program to plot the chart...
        Process pr = rt.exec("python Python/PlotCorrelation.py " + tickers + " " + start_date + " " + end_date);
    }
}
