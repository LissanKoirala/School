package com.gui;

// https://finnhub.io/docs/api/company-profile2


import com.stock.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StockInfo extends JPanel {
    // canvas for other GUI widgets

    JButton button;
    JTextField textfield;
    JLabel name, info, icon, chart;

    // TODO: Add a iframe and embed tradingview 
    public StockInfo(int width, int height, Asset asset) throws IOException {
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        name = new JLabel(asset.name);
        name.setBounds(130,45, 100000, 40);

        icon = new JLabel(asset.icon);
        icon.setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        icon.setBounds(20,50, 64, 64);


        // TODO: Either like this put an image, or just chart a rather simple chart...
        chart = new JLabel();
        chart.setIcon(new ImageIcon(new ImageIcon("data/default/chart.png").getImage().getScaledInstance(783, 415, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        chart.setBounds(20,200, 783, 415);



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
        add(chart);
    }
}
