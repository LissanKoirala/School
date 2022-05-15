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
    JLabel name;
    JLabel info;
    JLabel icon;

    public StockInfo(int width, int height, Stock stock) throws IOException {
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);

        name = new JLabel(stock.name);
        name.setBounds(10,50, 100000, 40);

        icon = new JLabel(stock.icon);
        icon.setIcon(new ImageIcon(stock.icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        icon.setBounds(20,120, 100, 100);



        if (stock.other_info_flag){
            JLabel country_exchange = new JLabel("Exchange : " + stock.exchange + "/" + stock.country);
            country_exchange.setBounds(10,60, 100000, 40);

            JLabel industry = new JLabel("Industry : " + stock.industry);
            industry.setBounds(10,70, 100000, 40);

            JLabel marketcap = new JLabel("MarketCap : " + stock.marketcap);
            marketcap.setBounds(10,80, 100000, 40);


            add(country_exchange);
            add(industry);
            add(marketcap);
        }



        add(name);
        add(icon);
    }
}