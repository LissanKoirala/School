package com.company;

public class Utils {
    public Utils(){
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

}
