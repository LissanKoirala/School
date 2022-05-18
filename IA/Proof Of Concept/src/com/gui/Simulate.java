package com.gui;

import com.analyzer.backtesting.SMACrossoverTester;
import com.analyzer.strategies.SMACrossover;
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

public class Simulate extends JPanel {
    // canvas for other GUI widgets

    FileHandler FileHandler = new FileHandler();

    JButton button;
    JTextField textfield;
    JLabel name, info, icon, chart;

    // TODO: Add a iframe and embed tradingview
    public Simulate(int width, int height, Asset asset) throws Exception {
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);


        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(asset);

        name = new JLabel(asset.name);
        name.setBounds(130,45, 100000, 40);

        icon = new JLabel(asset.icon);
        icon.setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        icon.setBounds(20,50, 64, 64);


        // TODO: Show an animation of this as it's happening... and a new thread will do the normal .simulate() call...
        // TODO: will keep the user busy while the real whole simulation runs...
        int[][] sma_to_show = {{20,50}, {50,180}};

        for (int[] ints : sma_to_show) {
            int sma1 = ints[0];
            int sma2 = ints[1];

            float[] result = smaCrossoverTester.test(sma1, sma2, false);
            float gain = result[0];
            System.out.println(sma1 + " " + sma2);
            System.out.println(gain);
            System.out.println(result[1]);
        }


        add(name);
        add(icon);
    }
}
