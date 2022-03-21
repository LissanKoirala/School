package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Stack Made out of Array");

        ArrayStack myStack = new ArrayStack(10);

        for (int i=0; i<10; i++) {
            System.out.println("Pushing " + i);
            myStack.push(i);
        }

        while (!myStack.isEmpty()) {
            System.out.println("Popping " + myStack.pop());
        }
    }
}