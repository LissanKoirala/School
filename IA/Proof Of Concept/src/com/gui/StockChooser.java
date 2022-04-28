package com.gui;

import com.stock.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockChooser extends JPanel {
    // canvas for other GUI widgets

    JButton button;
    JTextField textfield;
    JLabel label;

    public StockChooser(int width, int height) {
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);
        button = new JButton("Submit");
        button.setBounds(0,0, 100, 40);


        textfield = new JTextField();
        textfield.setBounds(120,0, 100, 30);


        label = new JLabel("");
        label.setBounds(0,50, 1000000, 40);


        add(button);
        add(textfield);
        add(label);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String textFieldValue = textfield.getText().toUpperCase();
                System.out.println(textFieldValue);

                try {
                    Stock stock = new Stock(textFieldValue);
                    String historical_data = stock.getHistorical_data();

                    label.setText(stock.info + "\n" + historical_data);


                } catch (Exception e) {
                    System.out.println("Stock Doesn't Exists"); // TODO: check if it's true if not print the error message
                }
            }
        });
    }
}