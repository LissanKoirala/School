package com.gui;

import com.asset.Asset;
import com.user.Watchlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        JLabel search = new JLabel("Search");
        search.setFont(new Font("Verdana", Font.BOLD, 20));
        search.setBounds(150, 50, 150, 50);
        add(search);

        button = new JButton("\uD83D\uDD0D"); // search icon
        button.setBounds(270,99, 50, 30);

        textfield = new JTextField();
        textfield.setBounds(150,100, 100, 30);

        // Showing watchlist on the side screen
        Watchlist Watchlists = new Watchlist("default");
        String[] watchlist = Watchlists.get();

        JLabel label = new JLabel("Watchlist");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setBounds(400, 50, 150, 50);
        add(label);

        int counter = 20;
        JButton[] watchlistlabel = new JButton[5];
        for (int i = 0; i < 5; i++) {

            Asset asset = new Asset(watchlist[i]);

            watchlistlabel[i] = new JButton("  " + asset.ticker);
            watchlistlabel[i].setFont(new Font("Verdana", Font.BOLD,12));
            watchlistlabel[i].setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            watchlistlabel[i].setBounds(400,(i*60)+100, 140, 50);
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
                    String type = Asset.type(textFieldValue);
                    Asset asset = Asset.create(textFieldValue);

                    GUICaller.AssetInfo(asset);

                } catch (Exception e) {
                    System.out.println("Stock Doesn't Exists"); // TODO: check if it's true if not print the error message
                    System.out.println(e);
                }
            }
        });


        // News TODO: Add this...
        JLabel news = new JLabel("News");
        news.setFont(new Font("Verdana", Font.BOLD, 20));
        news.setBounds(150, 130, 150, 50);
        add(news);

    }
}