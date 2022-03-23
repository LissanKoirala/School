package com.company;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] array = {1,2,3};
        Queue queue = new Queue(array);
        System.out.println(queue.isEmpty());
        queue.en_Queue(5);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        queue.de_Queue();
        System.out.println(queue.size());
    }
}
