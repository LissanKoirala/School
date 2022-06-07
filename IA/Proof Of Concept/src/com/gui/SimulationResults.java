package com.gui;

import com.analyzer.backtesting.SMACrossoverTester;
import com.asset.Asset;
import com.utils.FileHandler;
import com.utils.Utils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


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


        JLabel icon = new JLabel(asset.icon);
        icon.setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH))); // scaling the image properly so that there is no stretch
        icon.setBounds(100,120, 32, 32);
        add(icon);

        JLabel name = new JLabel(asset.name);
        name.setFont(new Font("Verdana", Font.BOLD, 15));
        name.setBounds(150, 110, 350, 50);
        add(name);


        SMACrossoverTester smaCrossoverTester = new SMACrossoverTester(asset);
        int[] data = smaCrossoverTester.simulate(); // does the simulation and saves it to a file...
        int bestsma1 = data[0];
        int bestsma2 = data[1];

        // file handler, read in the simulation results saved to a csv and show it in a table
        // converting into 2D parsed csv file
        String[][] simulation_results = Utils.convertToMultiDArrayFromCSV("data/stock/" + asset.ticker + "/simulation-sma.csv", 4);

        // Showing the best result
        JButton top = new JButton();
        top.setText("<html>" + bestsma1 + ", " + bestsma2 + ", " + paddGain(String.valueOf(data[2])) + ", " + data[3] + "</html>");
        top.setBounds(100,250, 310, 30);
        top.setHorizontalAlignment(SwingConstants.LEFT);
        top.setContentAreaFilled(false);
        top.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    GUICaller.Simulate(asset, bestsma1, bestsma2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(top);





        // Showing top 5 results...
        JButton[] results = new JButton[5];

        // top 5
        for (int i = 0; i<5; i++){

            results[i] = new JButton();
            String text = "<html>" + simulation_results[i][0] + ", " + simulation_results[i][1] + ", " + paddGain(simulation_results[i][2]) + ", " + simulation_results[i][3] + "</html>";
            results[i].setText(text);
//            results[i].setFont(new Font("Verdana", Font.BOLD,12));
            results[i].setBounds(100,(i*35)+285, 310, 30);
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
        export.setIcon(new ImageIcon(new ImageIcon("data/default/export.png").getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH))); // scaling the image properly so that there is no stretch
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


        // start_date and end_date selector

        JLabel start_time_l = new JLabel("Start time");
//        start_time.setFont(new Font("Verdana", Font.BOLD, 20));
        start_time_l.setBounds(100, 165, 150, 50);
        add(start_time_l);

        JLabel end_time_l = new JLabel("End Time");
//        end_time.setFont(new Font("Verdana", Font.BOLD, 20));
        end_time_l.setBounds(280, 165, 150, 50);
        add(end_time_l);



        UtilDateModel model = new UtilDateModel();
//model.setDate(20,04,2014);
// Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
// Don't know about the formatter, but there it is...
        JDatePickerImpl start_time = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        start_time.setBounds(100, 200, 120, 350);
        add(start_time);

        JDatePickerImpl end_time = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        end_time.setBounds(280, 200, 120, 350);
        add(end_time);

    }

    private String paddGain(String text){
        // padding the results
        float percentage_gain = Float.parseFloat(text);
        BigDecimal bd = new BigDecimal(percentage_gain);
        bd = bd.round(new MathContext(4)); // TODO: make this relative
        float rounded = bd.floatValue();

        String gain;

        // TODO: Use local files
        if (rounded > 0){
            gain = "<img src='" + new File("data/default/profit.png").toURI() + "' width='9' height='10'> " + rounded + " %";
        } else {
            gain = "<img src='" + new File("data/default/loss.png").toURI() + "' width='9' height='9'> " + rounded + " %";
        }
        return gain;
    }


}


// Code Required for the JDatePicker to work
// https://stackoverflow.com/a/26794863

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}