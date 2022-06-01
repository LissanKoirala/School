package com.gui;

import com.analyzer.backtesting.SMACrossoverTester;
import com.asset.Asset;
import com.utils.FileHandler;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class SimulationResults extends JPanel {

    int width, height;
    Asset asset;

    FileHandler FileHandler = new FileHandler();

    // TODO: Add a iframe and embed tradingview
    public SimulationResults(int width, int height, Asset asset) throws Exception {
        this.width = width;
        this.height = height;
        this.asset = asset;

        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);


        JLabel label = new JLabel("Backtesting");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setBounds(100, 50, 150, 50);
        add(label);

        JLabel type = new JLabel("SMA Crossover");
        type.setFont(new Font("Verdana", Font.BOLD, 12));
        type.setBounds(100, 75, 150, 50);
        add(type);


        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(asset);
        int[] data = smaCrossoverTester.simulate(); // does the simulation and saves it to a file...
        int bestsma1 = data[0];
        int bestsma2 = data[1];

        // file handler, read in the simulation results saved to a csv and show it in a table
        // converting into 2D parsed csv file
        String[][] simulation_results = Utils.convertToMultiDArrayFromCSV("data/stock/" + asset.ticker + "/simulation-sma.csv", 4);

        // Showing top 5 results...
        JButton[] results = new JButton[5];

        // top 5
        for (int i = 0; i<5; i++){
            results[i] = new JButton("" + simulation_results[i][0] + ", " + simulation_results[i][1] + ", " + simulation_results[i][2] + ", " + simulation_results[i][3]);
//            results[i].setFont(new Font("Verdana", Font.BOLD,12));
            results[i].setBounds(100,(i*35)+130, 310, 30);
            results[i].setHorizontalAlignment(SwingConstants.LEFT);
            results[i].setContentAreaFilled(false);
            int current = i;
            results[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    try {
                        GUICaller.Simulate(asset, Integer.parseInt(simulation_results[current][0]), Integer.parseInt(simulation_results[current][1]));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            add(results[i]);
        }


        // export option for further processing in external pieces of software if preferred by the user

        JButton export = new JButton("Export");
//            results[i].setFont(new Font("Verdana", Font.BOLD,12));
        export.setIcon(new ImageIcon(new ImageIcon("data/default/export.png").getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
        export.setBounds(300, 70, 100, 30);
        export.setHorizontalAlignment(SwingConstants.LEFT);
        export.setContentAreaFilled(false);
        export.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);

                File folderpath = f.getSelectedFile(); // returns the selected directory.
                System.out.println(folderpath);

                try {
                    FileHandler.copyFile(new File("data/stock/" + asset.ticker + "/simulation-sma.csv"), new File(folderpath + "/" + asset.ticker + "-simulation-results.csv"));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        add(export);


    }


}
