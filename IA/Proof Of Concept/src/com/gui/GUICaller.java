package com.gui;

import com.stock.Stock;

import javax.swing.*;

public class GUICaller {
    public GUICaller(){
    }

    public void StockChooser(){
        // demo GUI
        System.out.println("SEQUENCE: Program started");
        JFrame frame = new JFrame("Demo frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StockChooser myGUI = new StockChooser(600, 400);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
