package com.gui;

import com.stock.Stock;

import javax.swing.*;

public class GUICaller {
    public GUICaller(){
    }

    public void StockChooser(){
        // demo GUI
        System.out.println("SEQUENCE: Stock Chooser");
        JFrame frame = new JFrame("Stock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StockChooser myGUI = new StockChooser(600, 400);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public void Login(){
        // demo GUI
        System.out.println("SEQUENCE: Login");
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login myGUI = new Login(350, 150);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
