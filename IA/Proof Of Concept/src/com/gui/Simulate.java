package com.gui;

import com.analyzer.backtesting.SMACrossoverTester;
import com.analyzer.strategies.SMACrossover;
import com.analyzer.tools.SMA;
import com.stock.Asset;
import com.utils.FileHandler;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.Timer;

public class Simulate extends JPanel {
    // canvas for other GUI widgets

    int width, height;
    Asset asset;

    FileHandler FileHandler = new FileHandler();

    JButton button;
    JTextField textfield;
    JLabel name, info, icon, chart;

    // for paint canvas
    ArrayList<Float> close_prices = new ArrayList<>();
    ArrayList<Float> sma1 = new ArrayList<>();
    ArrayList<Float> sma2 = new ArrayList<>();

    float highest_data_point;
    float previous_highest_data_point;


    // TODO: Add a iframe and embed tradingview
    public Simulate(int width, int height, Asset asset) throws Exception {
        this.width = width;
        this.height = height;
        this.asset = asset;

        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);


        // TODO: Show an animation of this as it's happening... and a new thread will do the normal .simulate() call...
        // TODO: will keep the user busy while the real whole simulation runs...
//        int[][] sma_to_show = {{20,50}, {50,180}};

//        for (int[] ints : sma_to_show) {
//            int sma1 = ints[0];
//            int sma2 = ints[1];
//
//            float[] result = smaCrossoverTester.test(sma1, sma2, false);
//            float gain = result[0];
//            System.out.println(sma1 + " " + sma2);
//            System.out.println(gain);
//            System.out.println(result[1]);
//        }


        // TODO: Animate the stock close price like every 5 days or so, then also animate the sma lines, when there's a trade draw a vertical line of color...
        // TODO: Then below that stock graph, have a portfolio graph which too updates in the same frequency, if profit draw the lines in green if goes to loss draw the lines in red...

        Float[][] historical_data = asset.historical_data;

        // TODO: run the simulation for the highest returns in screen at last
        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(asset);
        int[] data = smaCrossoverTester.simulate();
        int bestsma1 = data[0];
        int bestsma2 = data[1];

        System.out.println(bestsma1 + " , " + bestsma2);
        SMA SMA_1 = new SMA(bestsma1);
        SMA SMA_2 = new SMA(bestsma2);

        ArrayList<Float> sma_data_1 =  SMA_1.getSMAData(asset);
        ArrayList<Float> sma_data_2 =  SMA_2.getSMAData(asset);


//        // Found this... source : https://stackoverflow.com/a/21801845

        Timer timer = new Timer(5, new ActionListener() {
            int counter = 0; // counting the iterations to get the index of the data for next loop
            public void actionPerformed(ActionEvent e) {
                if (false) { // when you want to stop it...
                    ((Timer) e.getSource()).stop();
                } else {
                    // TODO: add to the arraylist of lines... then repaint...

                    close_prices.add(historical_data[counter][5]);
                    sma1.add(sma_data_1.get(counter));
                    sma2.add(sma_data_2.get(counter));

                    counter++;
                    repaint();

                }
            }
        });



        JButton start = new JButton("Simulate");
        start.setBounds(130,80,80,40);

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
        add(start);


        // So when the animation is running for a few will be running on screen, it does the actual simulation

    }

    // Basically calculates the sma crossover over and shows the results on screen as it does it...
    // TODO: name the method as simulateSMACrossover
    // TODO: do the test() function but in one for loop?
    // TODO: there is no need copy paste the test() code, you just find the sma1 and sma2 and loop through the price array, then on each loop count, check it sma crossover or not, if happens, draw the vertical line, take the trade, then update the money value every day after based on the price of the stock, then chart the stock price, the smas and below chart the value of the money as the days pass...
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        for (Line line : lines) {
//            // TODO: Draw a horizontal line at the 0 point in the middle of the screen...
//            // TODO: probably make the line a 2D array, one the value and another one the colour, as profit (above the zero horizontal line) will be green and loss red
//            line.drawLine(g);
//        }
//

        // checking if need to clear the panel to avoid multiple lines from appearing...
        if (highest_data_point != previous_highest_data_point){
            super.paintComponent(g);
            repaint();
            previous_highest_data_point = highest_data_point;
        }

        if (!close_prices.isEmpty()) {

            float[] highest_and_lowest = Utils.findHighestAndLowest(close_prices);

            highest_data_point = highest_and_lowest[0];
            float lowest_data_point = highest_and_lowest[1];

            int max_y_point = 630; // getting the point in the middle... making that tha base...
            float previous_close = (close_prices.get(0) / highest_data_point * (height / 2)) + 10; // getting the first data point as previous close so that it doesn't start from 0
            float previous_sma1 = (sma1.get(0) / highest_data_point * (height / 2)) + 10;
            float previous_sma2 = (sma2.get(0) / highest_data_point * (height / 2)) + 10;

            int day_counter = 0;

            for (int i = 0; i<close_prices.size(); i++) {
                // printing the stock price
                float close_price = (close_prices.get(i) / highest_data_point * (height / 2)) + 10; // 5 for close price
                g.setColor(Color.BLACK);
                g.drawLine(day_counter, max_y_point - (int) previous_close, day_counter + 1, max_y_point - (int) close_price); // TODO: adjust the +2 based on the number of data points
                previous_close = close_price;


                // printing the sma1
                float paintsma1 = (sma1.get(i) / highest_data_point * (height / 2)) + 10; // 5 for close price
                g.setColor(Color.RED);
                g.drawLine(day_counter, max_y_point - (int) previous_sma1, day_counter + 1, max_y_point - (int) paintsma1); // TODO: adjust the +2 based on the number of data points
                previous_sma1 = paintsma1;


                // printing the sma2
                float paintsma2 = (sma2.get(i) / highest_data_point * (height / 2)) + 10; // 5 for close price
                g.setColor(Color.GREEN);
                g.drawLine(day_counter, max_y_point - (int) previous_sma2, day_counter + 1, max_y_point - (int) paintsma2); // TODO: adjust the +2 based on the number of data points
                previous_sma2 = paintsma2;


                day_counter++;

            }
        }

    }



}
