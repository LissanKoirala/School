package com.gui;

import com.asset.Asset;
import com.asset.Calendar;
import com.asset.NewsData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.user.Watchlist;
import com.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: Considering making this the home page so will include other info as well...
public class HomeScreen extends JPanel {
    // canvas for other GUI widgets

    JButton button;
    JTextField textfield;
    JLabel label;
    JLabel label1;

    public HomeScreen(int width, int height) throws Exception {
        System.out.println("SEQUENCE: GUI constructor");
        this.setPreferredSize(new Dimension(width, height));
        setLayout(null);


        // Market Hours
        // TODO: add the gif and image for open and close
        String[] marketStatus = Calendar.marketStatus();
        String open_or_close = marketStatus[0];
        String next_open_or_close = marketStatus[1];

        JLabel open_or_close_label = new JLabel(" " + open_or_close);
        ;
        if (open_or_close.equals("Market Closed")){
            open_or_close_label.setIcon(new ImageIcon(new ImageIcon("data/default/red.png").getImage().getScaledInstance(8, 8, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            open_or_close_label.setBounds(360,90, 100, 30);

        } else {
            open_or_close_label.setIcon(new ImageIcon(new ImageIcon("data/default/green.jpg").getImage().getScaledInstance(8, 8, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            open_or_close_label.setBounds(350,90, 110, 30);
        }

//        open_or_close_label.setFont(new Font("Verdana", Font.BOLD, 20));
        open_or_close_label.setHorizontalAlignment(SwingConstants.RIGHT);
        add(open_or_close_label);






        JLabel search = new JLabel("Search");
        search.setFont(new Font("Verdana", Font.BOLD, 20));
        search.setBounds(60, 50, 150, 50);
        add(search);

        textfield = new JTextField();
        textfield.setMargin(new Insets(2,13,3,2)); // padding the text
        textfield.setBounds(60,100, 150, 30);

        JLabel recommendation = new JLabel("Recommendation: ");
        recommendation.setBounds(60,30, 250, 30);
        add(recommendation);



        // TODO: currently only auto capitalizes, might add suggestions etc...
        textfield.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isLowerCase(keyChar)) {
                    e.setKeyChar(Character.toUpperCase(keyChar));
                }

                // TODO: Add a method for the recommendation
                String recommendations = searchTickerOrName(textfield.getText() + keyChar);
                recommendation.setText(recommendations);


            }
        });




        button = new JButton("\uD83D\uDD0D"); // search icon
        button.setBounds(230,99, 50, 30);

        // Showing watchlist on the side screen
        Watchlist Watchlists = new Watchlist("default");
        String[] watchlist = Watchlists.get();

        JLabel label = new JLabel("Watchlist");
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        label.setBounds(500, 50, 150, 50);
        add(label);

        int counter = 20;
        JButton[] watchlistlabel = new JButton[5];
        for (int i = 0; i < 5; i++) {

            Asset asset = Asset.create(watchlist[i]);

            watchlistlabel[i] = new JButton("  " + asset.ticker);
            watchlistlabel[i].setFont(new Font("Verdana", Font.BOLD,12));
            watchlistlabel[i].setIcon(new ImageIcon(asset.icon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))); // scaling the image properly so that there is no stretch
            watchlistlabel[i].setBounds(500,(i*61)+100, 140, 50);
            watchlistlabel[i].setHorizontalAlignment(SwingConstants.LEFT);
            watchlistlabel[i].setContentAreaFilled(false); // TODO: Try how this differs for MacOS



            int finalI = i;
            watchlistlabel[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        GUICaller.AssetInfo(Asset.create(watchlist[finalI]));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            add(watchlistlabel[i]);
        }



        add(button);
        add(textfield);


        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String textFieldValue = textfield.getText().toUpperCase();
                System.out.println(textFieldValue);

                try {
                    Asset asset = Asset.create(textFieldValue);
                    GUICaller.AssetInfo(asset);

                } catch (Exception e) {
                    System.out.println("Stock Doesn't Exists"); // TODO: check if it's true if not print the error message
                    System.out.println(e);
                }
            }
        });


        // News TODO: Add this...
        // TODO: make a method to return unique news, no stock overlaps cause sometimes getting news from same company
        JLabel news = new JLabel("News");
        news.setFont(new Font("Verdana", Font.BOLD, 20));
        news.setBounds(60, 130, 150, 50);
        add(news);

        NewsData NewsData = new NewsData();
        System.out.println(Arrays.toString(watchlist));
        counter = 20;
        JButton[] newslabel = new JButton[5];
        JLabel[] newsicon = new JLabel[5];

        // getting the news data
        ArrayList<JsonObject> all_newsdata = new ArrayList<>();

        // TODO: worth mentioning in criterion C, the newer method of getting the news
        int limit = 5;
        JsonArray response = NewsData.get(Watchlists.getAsString(), limit);
        System.out.println(response);
        for (int i = 0; i<5; i++) {
            all_newsdata.add(response.get(0).getAsJsonObject().get("news").getAsJsonArray().get(i).getAsJsonObject());
        }

        // only add the ones with content and image
        ArrayList<JsonObject> newsdata = new ArrayList<>();
        int count = 0;
        for (JsonObject n: all_newsdata){
            if (count < 2){
                try {
                    if (!n.get("summary").getAsString().equals("") || !n.get("summary").getAsString().equals("\n")) {
                        n.get("images").getAsJsonArray().get(2).getAsJsonObject().get("url").getAsString();
                        newsdata.add(n);
                        count++;
                    }
                } catch (Exception ignored){} // do nothing if it's not in it
            }
        }


        for (int i = 0; i < 2; i++) {

            System.out.println(newsdata.get(i));
            String header = "", summary = "", link = "", image = null;
            try {
                header = newsdata.get(i).get("headline").getAsString();
                summary = newsdata.get(i).get("summary").getAsString();
                link = newsdata.get(i).get("url").getAsString();
                try {
                    image = newsdata.get(i).get("images").getAsJsonArray().get(2).getAsJsonObject().get("url").getAsString();
                } catch (Exception e){
                    System.out.println("No image found for the news");
                }

            } catch (Exception e){
                System.out.println("Error fetching info from news");
                System.out.println(e);
            }

            // Truncating the header and summary
            if (header.length() > 71){
                String temp = header.substring(0, 70).strip();
                header = temp + "…";
            }

            if (summary.length() > 145){
                String temp = summary.substring(0, 144).strip();
                summary = temp + "…";
            }

            newslabel[i] = new JButton();
            String imageInHtml = "<html>" +
                    "<style>h5 {\n" +
                    "font: 8px Verdana;\n" +
                    "margin-top: -12px;\n" +
                    "padding-right: 65px;\n"+
                    "};\n" +
                    "</style>" +

                    "<style>h4 {\n" +
                    "font: 9px Verdana;\n" +
                    "padding-right: 65px;\n"+
                    "font-weight: bold;\n"+
                    "};\n" +
                    "</style>"+
                    "<h4>" + header + "</h4> <h5>" + summary + "</h5> " +
                    "</html>";

            newslabel[i].setText(imageInHtml);

            newslabel[i].setBounds(60,(i*110)+180, 400, 105);
            String finalLink = link;
            newslabel[i].addActionListener(new ActionListener(){ // opens the news in browser
                public void actionPerformed(ActionEvent ae){
                    openWebpage(URI.create(finalLink));
                }
            });
//            newslabel[i].setHorizontalAlignment(SwingConstants.LEFT);
            newslabel[i].setContentAreaFilled(false); // TODO: Try how this differs for MacOS
            add(newslabel[i]);


            // the image
            //String local_path = Utils.downloadFromLink(image); // downloading the image locally
            if (image != null) {
                BufferedImage news_image;
                System.out.println(image);
                URL url = new URL(image);
                news_image = ImageIO.read(url);

                newsicon[i] = new JLabel();
                newsicon[i].setIcon(new ImageIcon(new ImageIcon(news_image).getImage().getScaledInstance(93, 70, Image.SCALE_SMOOTH))); // scaling the image properly so that there is no stretch
                // newsicon[i].setIcon(new ImageIcon(news_image));
                newsicon[i].setBounds(360, (i * 110) + 205, 93, 70);
                add(newsicon[i]);
            }


        }


    }

    // Add a text field that auto updates as you type, like below the JTextField, instead of the autocomplete thing I was planning about...
    public String searchTickerOrName(String input){

        String[][] stock_l = Utils.convertToMultiDArrayFromCSV("data/default/stocks.csv", 2);
        String[][] crypto = Utils.convertToMultiDArrayFromCSV("data/default/crypto.csv", 2);
        String[][] forex = Utils.convertToMultiDArrayFromCSV("data/default/forex.csv", 2);

        String[][] combined = new String[stock_l.length + crypto.length + forex.length][3];

        // combining stock and crypto
        // TODO: missing 1 value at the start
        for(int j = 0; j < stock_l.length + crypto.length + forex.length; j++){
            if (j < stock_l.length){
                combined[j][0] = stock_l[j][0];
                combined[j][1] = stock_l[j][1];
                combined[j][2] = "stock";

            } else if (j > stock_l.length && j < stock_l.length + crypto.length){
                combined[j][0] = crypto[j-(stock_l.length)][0];
                combined[j][1] = crypto[j-(stock_l.length)][1];
                combined[j][2] = "crypto";

            } if (j > stock_l.length + crypto.length){
                combined[j][0] = forex[j-(stock_l.length + crypto.length)][0];
                combined[j][1] = forex[j-(stock_l.length + crypto.length)][1];
                combined[j][2] = "forex";
            }
        }

        ArrayList<String> matches = new ArrayList<>(); // using ArrayList because we don't know the size yet...

        // TODO: find matches for tickers & names
        int counter = 0;

        for (String[] element : combined){

            if (counter > 5){
                break;
            }

            if (element[0] != null) {
                if (element[0].toLowerCase().startsWith(input.toLowerCase())) { // TODO: this works but would be better to implement the contains yourself
                    matches.add(element[0]);
                    counter++;
                }
                // if not found, try searching for the matching string in the stock name
                else if (element[1].toLowerCase().startsWith(input.toLowerCase())) { // TODO: this works but would be better to implement the contains yourself
                    matches.add(element[0]);
                    counter++;
                }
            }
        }

        System.out.println(matches);



        return String.valueOf(matches);

    }


    // https://stackoverflow.com/a/10967469
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
