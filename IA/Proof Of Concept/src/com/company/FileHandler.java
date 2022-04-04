package com.company;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;

public class FileHandler {

    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> data = new ArrayList<>(); // initiating the arary

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            while (line != null) {
                data.add(line); // Adding each line to the arraylist
                line = br.readLine();
            }
        }
        catch (IOException e) { // Handling io exceptions
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public void writeToFile(String fileName, String text, boolean append) {
        // write text to fileName
        // either overwriting (append = false)
        // or appending (append = true)
        // using all the exception handling best practices

        try (PrintWriter pr = new PrintWriter(new FileWriter(fileName, append))) {
            pr.println(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromLine(String fileName, int lineNumber){
        ArrayList<String> data = readFromFile(fileName);
        return data.get(lineNumber-1); // Line 2, is index 1
    }

}
