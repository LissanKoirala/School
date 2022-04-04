package com.company;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PersistentArrayList {
    private FileHandler handler;
    private ArrayList<String> data = new ArrayList<>();

    public PersistentArrayList(){
        this.handler = new FileHandler();
        handler.writeToFile("data.txt", "", false);
    }

    public void save(){
        for(String line : data){
            handler.writeToFile("data.txt", line, false);
        }
    }

    public void add(String element){
        data.add(element);
        System.out.println(element);
        save();
    }

    public void delete(){
        data.clear();
        handler.writeToFile("data.txt", "", false);
    }

    public int size() {
        int counter = 0;

        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                counter++;
                line = br.readLine();
            }
        }
        catch (IOException e) { // Handling io exceptions
            System.out.println("Error occured.");
            e.printStackTrace();
        }

        return counter;
    }
}
