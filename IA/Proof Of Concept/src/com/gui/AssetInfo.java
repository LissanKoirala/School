package com.gui;

// https://finnhub.io/docs/api/company-profile2


import com.asset.Asset;
import com.utils.FileHandler;
import com.utils.Utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class AssetInfo extends JPanel {
    // canvas for other GUI widgets

    int width;
    int height;

    Asset asset;

    FileHandler FileHandler = new FileHandler();

    JButton button;
    JTextField textfield;
    JLabel name, info, icon, chart;

    // TODO: Add a iframe and embed tradingview 
    public AssetInfo(int width, int height, Asset asset) throws IOException {
        this.width = width;
        this.height = height;
        this.asset = asset;

        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        name = new JLabel(asset.name);
        name.setBounds(130,45, 100000, 40);

        icon = new JLabel(asset.icon);
        icon.setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        icon.setBounds(20,50, 64, 64);

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



        if (asset.other_info_flag){
            JLabel country_exchange = new JLabel("Exchange : " + asset.exchange);
            country_exchange.setBounds(130,65, 100000, 40);

            JLabel industry = new JLabel("Industry : " + asset.industry);
            industry.setBounds(130,80, 100000, 40);

            JLabel marketcap = new JLabel("MarketCap : " + asset.marketcap);
            marketcap.setBounds(130,95, 100000, 40);


            add(country_exchange);
            add(industry);
            add(marketcap);
        }



        add(name);
        add(icon);

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
