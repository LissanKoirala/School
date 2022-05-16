package com.gui;

// https://finnhub.io/docs/api/company-profile2


import com.stock.Stock;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class StockInfo extends JPanel {
    // canvas for other GUI widgets

    JButton button;
    JTextField textfield;
    JLabel name, info, icon, chart;

    // TODO: Add a iframe and embed tradingview 
    public StockInfo(int width, int height, Stock stock) throws IOException {
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        name = new JLabel(stock.name);
        name.setBounds(130,45, 100000, 40);

        icon = new JLabel(stock.icon);
        icon.setIcon(new ImageIcon(stock.icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        icon.setBounds(20,50, 100, 100);


        // TODO: Either like this put an image, or just chart a rather simple chart...
        chart = new JLabel();
        chart.setIcon(new ImageIcon(new ImageIcon("data/default/chart.png").getImage().getScaledInstance(783, 415, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        chart.setBounds(20,200, 783, 415);



        if (stock.other_info_flag){
            JLabel country_exchange = new JLabel("Exchange : " + stock.exchange + "/" + stock.country);
            country_exchange.setBounds(130,65, 100000, 40);

            JLabel industry = new JLabel("Industry : " + stock.industry);
            industry.setBounds(130,80, 100000, 40);

            JLabel marketcap = new JLabel("MarketCap : " + stock.marketcap);
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
