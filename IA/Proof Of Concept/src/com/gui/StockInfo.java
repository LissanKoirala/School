package com.gui;

// https://finnhub.io/docs/api/company-profile2


import com.stock.Asset;
import com.utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class StockInfo extends JPanel {
    // canvas for other GUI widgets

    FileHandler FileHandler = new FileHandler();

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


        // TODO: Implement my own in python and open an api endpoint...
        // Options : [1m,3m,5m,15m,30m,45m,1h,2h,3h,4h,1d,1w]
        String url = "https://api.chart-img.com/v1/tradingview/advanced-chart?interval=1d&height=550&width=800&theme=light&format=png&key=00e94094-4d2c-4658-b32e-776be716517b&symbol="+asset.ticker;

        File directory = new File("data/temp/");
        if (! directory.exists()){
            directory.mkdirs();
        }

        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get("data/temp/chart.png"), StandardCopyOption.REPLACE_EXISTING);
        }

        chart = new JLabel(new ImageIcon(new ImageIcon("data/temp/chart.png").getImage().getScaledInstance(560, 385, Image.SCALE_SMOOTH)));  // scaling the image properly so that there is no stretch
        chart.setBounds(20,200, 560, 385);





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
