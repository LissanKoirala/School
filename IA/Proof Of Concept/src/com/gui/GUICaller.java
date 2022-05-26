package com.gui;

import com.stock.Asset;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUICaller {
    public GUICaller(){
    }

    public static void startup(){
        // TODO: Start everything off here, manage caching etc... Start instantiating assets from watchlist etc...
        Login();
    }

    public static void StockChooser(){
        System.out.println("SEQUENCE: Stock Chooser");
        JFrame frame = new JFrame("Stock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StockChooser myGUI = new StockChooser(300, 50);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public static void StockInfo(Asset asset) throws IOException {
        System.out.println("SEQUENCE: Stock Info");
        JFrame frame = new JFrame("Stock");
        StockInfo myGUI = new StockInfo(600, 630, asset);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }

    public static void Simulate(Asset asset) throws Exception {
        System.out.println("SEQUENCE: Simulate");
        JFrame frame = new JFrame("Backdating");
        Simulate myGUI = new Simulate(600, 630, asset);
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }


    public static void Login(){
        System.out.println("SEQUENCE: Login");
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login myGUI = new Login(350, 150, frame); // passing the frame so that it can close itself...
        frame.add(myGUI);
        frame.pack();
        frame.setVisible(true);
    }
}
