package com.gui;

// https://finnhub.io/docs/api/company-profile2


import com.asset.Asset;
import com.asset.Calendar;
import com.utils.FileHandler;
import com.utils.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.abs;

// TODO: Useful info: https://www.cnbc.com/quotes/TSLA?tab=ownership & on Webull

public class AssetInfo extends JPanel {
    // canvas for other GUI widgets

    int width;
    int height;

    Asset asset;

    FileHandler FileHandler = new FileHandler();

    JButton button, simulate;
    JTextField textfield;
    JLabel name, info, icon, chart;

    // TODO: Add a iframe and embed tradingview 
    public AssetInfo(int width, int height, Asset asset) throws Exception {
        this.width = width;
        this.height = height;
        this.asset = asset;

        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);


        icon = new JLabel(asset.icon);
        icon.setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        icon.setBounds(70,50, 64, 64);
        add(icon);

        name = new JLabel(asset.name);
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setBounds(170, 40, 500, 40);
        add(name);

        JLabel country_exchange = new JLabel("Exchange : " + asset.exchange);
        country_exchange.setHorizontalAlignment(SwingConstants.LEFT);
        country_exchange.setBounds(170, 60, 100000, 40);
        add(country_exchange);

        // TODO: worth mentioning in Criterion C?
        if ((asset.type.equals("us_equity") && Calendar.isMarketOpen()) || asset.type.equals("crypto")){
            JLabel price = new JLabel("Price : " + asset.price());
            price.setHorizontalAlignment(SwingConstants.LEFT);
            price.setBounds(170, 80, 100000, 40);
            add(price);
        }

        // TODO: bug in the graphics
//        if (asset.about != null){
//            JLabel about = new JLabel();
//            about.setText("<html><h5>" + asset.about + "</h5></html>");
//            about.setBounds(170, 120, 300, 70);
//            add(about);
//        }


        // Using external api for charts...
//        // TODO: Implement my own in python and open an api endpoint...
//        // Options : [1m,3m,5m,15m,30m,45m,1h,2h,3h,4h,1d,1w]
//        String url = "https://api.chart-img.com/v1/tradingview/advanced-chart?interval=1d&height=550&width=800&theme=light&format=png&key=00e94094-4d2c-4658-b32e-776be716517b&symbol="+asset.ticker;
//
//        File directory = new File("data/temp/");
//        if (! directory.exists()){
//            directory.mkdirs();
//        }
//
//        try (InputStream in = new URL(url).openStream()) {
//            Files.copy(in, Paths.get("data/temp/chart.png"), StandardCopyOption.REPLACE_EXISTING);
//        }
//
//        chart = new JLabel(new ImageIcon(new ImageIcon("data/temp/chart.png").getImage().getScaledInstance(560, 385, Image.SCALE_SMOOTH)));  // scaling the image properly so that there is no stretch
//        chart.setBounds(20,200, 560, 385);
//        add(chart);




        // TODO: Organise the action listener, make the class implement Action Listener
        simulate = new JButton("Simulate"); // TODO: or call it Backtesting/Backtracking?
        simulate.setFont(new Font("Verdana", Font.BOLD,12));
        simulate.setBounds(170,120, 95, 25);

        simulate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    GUICaller.SimulationResults(asset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        add(simulate);


    }


    // TODO: make a algorithm that limits the lines from shooting off the graph, limit the max x and y values according to the prices overtime
    // TODO: for eg: BTC's price of 30,000 shoots off the graph... make it relative, probably graph the percentage increase?
    // TODO: find the highest and lowest point, plot the percentage increase
    // TODO: just find the higest point and divide everything by it...
    public void paintComponent(Graphics g){ // paints the stock chart
        ArrayList<Float> all_historical_data = asset.getHistorical_data(5);
        ArrayList<Float> historical_data = Utils.stripArrayList(all_historical_data, 550, false);


        float[] highest_and_lowest = Utils.findHighestAndLowest(asset.getHistorical_data(5));

        float highest_data_point = highest_and_lowest[0];
        float lowest_data_point = highest_and_lowest[1];

        int max_y_point = 630; // getting the point in the middle... making that tha base...
        float previous_close = (historical_data.get(0) /highest_data_point * (height/2))+10; // getting the first data point as previous close so that it doesn't start from 0
        int day_counter = 20;

        for (Float daily_data : historical_data) {
            float close_price = (daily_data/highest_data_point * (height/2))+10; // 5 for close price
            g.drawLine(day_counter, max_y_point-(int) previous_close, day_counter+1, max_y_point-(int) close_price); // TODO: adjust the +2 based on the number of data points

            previous_close = close_price;
            day_counter++;
        }
    }
}
