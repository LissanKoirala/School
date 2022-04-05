package com.utils;

import java.util.ArrayList;

public class Math {
    public Math(){
    }

    public float average(ArrayList<Integer> numbers) {
        float sum = 0;

        //compute sum
        for(int num:numbers) {
            sum += num;
        }

        //compute average
        return (sum / numbers.size());
    }

}
