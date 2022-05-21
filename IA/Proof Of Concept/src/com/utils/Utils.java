package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Utils {
    public Utils(){
    }

    // convert to multi Dimension array
    public String[][] convertToMultiDArray(String fileName, int column){ // only needs columns cause can find row with preious methods
        FileHandler FileHandler = new FileHandler();

        int row = 0;
        String[][] array = new String[FileHandler.getRowNumber(fileName)][column];

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while((line=bufferedReader.readLine()) != null){
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while(tokenizer.hasMoreTokens()){
                    for(int col = 0; col<column; col++){
                        array[row][col] = tokenizer.nextToken();
                    }
                }
                row++;
            }

        } catch (Exception e){
            System.out.println(e);
        }

        return array;
    }



    public Float[][] convertStringArrayToFloatArray(String[][] array){ // returns a float arraylist

        Float[][] floatArray = new Float[array.length][array[0].length]; // figuring out how big the original array was

        for (int x = 0; x < array.length; x++){
            for (int y = 0; y < array[x].length; y++){
                if (y == 0) { // if it's a date, convert to unix timestamp...
                    floatArray[x][y] = new Float(0); // TODO: change this to actual time
                } else {
                    floatArray[x][y] = new Float(array[x][y]);
                }
            }
        }

        return floatArray;
    }




    // Bubble sort to sort either in ascending or descending
    public int[] sortArray(int[] array, boolean ascending){
        boolean swapped = true;
        int sorted_count = 0;

        while(swapped){
            swapped = false; // set the initial value to false as if it doesn't change to true, nothing has been saved.

            for(int i=0; i<array.length-1-sorted_count; i++){
                int a = array[i];
                int b = array[i+1];

                // Sorting in ascending order or descending order
                if(ascending){ // If ascending is true, make sure 1st is smaller then 2nd
                    if(a > b){
                        array[i] = b;
                        array[i+1] = a;
                        swapped = true;
                    }
                } else{ // This is if the ascending, so makes sure the 1st number is bigger than the 2nd number
                    if(a < b){
                        array[i] = b;
                        array[i+1] = a;
                        swapped = true;
                    }
                }
            }
            sorted_count++;
        }
        return array;
    }

    public static float[] findHighestAndLowest(float[] data){
        float highest = data[0];
        float lowest = data[0]; // make it the first number of the element can't be zero numbers might always be bigger than zero,
        // TODO: lead to getting divide by zero... by putting default value as 0
        for(float num: data){
            if (num > highest){
                highest = num;
            }
            else if (num < lowest){
                lowest = num;
            }
        }

        return new float[]{highest, lowest};
    }

}
