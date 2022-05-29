package com.gui;

import com.asset.Asset;
import com.asset.Calendar;
import com.asset.NewsData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.user.Watchlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

// TODO: Considering making this the home page so will include other info as well...
public class HomeScreen extends JPanel {
    // canvas for other GUI widgets

    JButton button;
    JTextField textfield;
    JLabel label;
    JLabel label1;

    public HomeScreen(int width, int height) throws Exception {
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);


        // Market Hours
        // TODO: add the gif and image for open and close
        String[] marketStatus = Calendar.isMarketOpen();
        String open_or_close = marketStatus[0];
        String next_open_or_close = marketStatus[1];

        JLabel open_or_close_label = new JLabel(" " + open_or_close);
        ;
        if (open_or_close.equals("Market Closed")){
            open_or_close_label.setIcon(new ImageIcon(new ImageIcon("data/default/red.png").getImage().getScaledInstance(8, 8, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            open_or_close_label.setBounds(360,90, 100, 30);

        } else {
            open_or_close_label.setIcon(new ImageIcon(new ImageIcon("data/default/green.jpg").getImage().getScaledInstance(8, 8, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            open_or_close_label.setBounds(350,90, 110, 30);
        }

//        open_or_close_label.setFont(new Font("Verdana", Font.BOLD, 20));
        open_or_close_label.setHorizontalAlignment(SwingConstants.RIGHT);
        add(open_or_close_label);






        JLabel search = new JLabel("Search");
        search.setFont(new Font("Verdana", Font.BOLD, 20));
        search.setBounds(60, 50, 150, 50);
        add(search);

        textfield = new JTextField();
        textfield.setBounds(60,100, 150, 30);

        button = new JButton("\uD83D\uDD0D"); // search icon
        button.setBounds(230,99, 50, 30);

        // Showing watchlist on the side screen
        Watchlist Watchlists = new Watchlist("default");
        String[] watchlist = Watchlists.get();

        JLabel label = new JLabel("Watchlist");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setBounds(500, 50, 150, 50);
        add(label);

        int counter = 20;
        JButton[] watchlistlabel = new JButton[5];
        for (int i = 0; i < 5; i++) {

            Asset asset = Asset.create(watchlist[i]);

            watchlistlabel[i] = new JButton("  " + asset.ticker);
            watchlistlabel[i].setFont(new Font("Verdana", Font.BOLD,12));
            watchlistlabel[i].setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            watchlistlabel[i].setBounds(500,(i*61)+100, 140, 50);
            watchlistlabel[i].setHorizontalAlignment(SwingConstants.LEFT);
            watchlistlabel[i].setContentAreaFilled(false); // TODO: Try how this differs for MacOS



            int finalI = i;
            watchlistlabel[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        GUICaller.AssetInfo(Asset.create(watchlist[finalI]));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            add(watchlistlabel[i]);
        }



        add(button);
        add(textfield);


        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String textFieldValue = textfield.getText().toUpperCase();
                System.out.println(textFieldValue);

                try {
                    Asset asset = Asset.create(textFieldValue);
                    GUICaller.AssetInfo(asset);

                } catch (Exception e) {
                    System.out.println("Stock Doesn't Exists"); // TODO: check if it's true if not print the error message
                    System.out.println(e);
                }
            }
        });


        // News TODO: Add this...
        // TODO: make a method to return unique news, no stock overlaps cause sometimes getting news from same company
        JLabel news = new JLabel("News");
        news.setFont(new Font("Verdana", Font.BOLD, 20));
        news.setBounds(60, 130, 150, 50);
        add(news);

        NewsData NewsData = new NewsData();
        System.out.println(Arrays.toString(watchlist));
        counter = 20;
        JButton[] newslabel = new JButton[5];
        for (int i = 0; i < 2; i++) {

            JsonArray response = NewsData.get(watchlist[i], 1);
            JsonObject newsdata = response.get(0).getAsJsonObject().get("news").getAsJsonArray().get(0).getAsJsonObject();

            String header = newsdata.get("headline").getAsString();
            String summary = newsdata.get("summary").getAsString();
            String image = newsdata.get("images").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();

            // Truncating the header and summary
            if (header.length() > 71){
                String temp = header.substring(0, 70).strip();
                header = temp + "…";
            }

            if (summary.length() > 154){
                String temp = summary.substring(0, 153).strip();
                summary = temp + "…";
            }


            newslabel[i] = new JButton();
            String imageInHtml = "<html>" +
                    "<style>h5 {\n" +
                    "font: 8px Verdana;\n" +
                    "margin-top: -12px;\n" +
                    "padding-right: 60px;\n"+
                    "};\n" +
                    "</style>" +

                    "<style>h4 {\n" +
                    "font: 10px Verdana;\n" +
                    "padding-right: 60px;\n"+
                    "font-weight: bold;\n"+
                    "};\n" +
                    "</style>"+
                    "<h4>" + header + "</h4> <h5>" + summary + "</h5> " +
                    "</html>";

            newslabel[i].setText(imageInHtml);

//            newslabel[i].setIcon(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            newslabel[i].setBounds(60,(i*115)+180, 400, 100);
//            newslabel[i].setHorizontalAlignment(SwingConstants.LEFT);
            newslabel[i].setContentAreaFilled(false); // TODO: Try how this differs for MacOS
            add(newslabel[i]);
        }


    }
}