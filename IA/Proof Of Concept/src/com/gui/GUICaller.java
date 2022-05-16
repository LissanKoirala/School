package com.gui;

import com.stock.Stock;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class GUICaller {
    public GUICaller(){
    }

    public void StockChooser(){
        System.out.println("SEQUENCE: Stock Chooser");
        JFrame frame = new JFrame("Stock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StockChooser myGUI = new StockChooser(300, 50);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public void StockInfo(Stock stock) throws IOException {
        System.out.println("SEQUENCE: Stock Info");
        JFrame frame = new JFrame("Stock");
        StockInfo myGUI = new StockInfo(850, 630, stock);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public void Login(){
        System.out.println("SEQUENCE: Login");
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login myGUI = new Login(350, 150);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
