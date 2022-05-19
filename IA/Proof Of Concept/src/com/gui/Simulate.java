package com.gui;

import com.analyzer.backtesting.SMACrossoverTester;
import com.analyzer.strategies.SMACrossover;
import com.stock.Asset;
import com.utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.Timer;

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

        // So when the animation is running for a few will be running on screen, it does the actual simulation
        // TODO: run the simulation for the highest returns in screen at last
        int[] data = smaCrossoverTester.simulate();

    }

    // Basically calculates the sma crossover over and shows the results on screen as it does it...
    // TODO: name the method as simulateSMACrossover
    // TODO: do the test() function but in one for loop?
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        for (Line line : lines) {
//            // TODO: Draw a horizontal line at the 0 point in the middle of the screen...
//            // TODO: probably make the line a 2D array, one the value and another one the colour, as profit (above the zero horizontal line) will be green and loss red
//            line.drawLine(g);
//        }
//
//    }


//    // Found this... source : https://stackoverflow.com/a/21801845
//    Timer timer = new Timer(75, new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            if (false) { // when you want to stop it...
//                ((Timer) e.getSource()).stop();
//            } else {
//                // TODO: add to the arraylist of lines... then repaint...
//                repaint();
//            }
//        }
//    });


}
