package com.company;

public class ArrayStack {
    private int maxSize;
    private int[] elements;
    private int top;

    public ArrayStack(int size) {
        maxSize = size;
        elements = new int[maxSize];
        top = -1;
    }

    public boolean push(int n) { // also returns true or false, if it was success or not
        if (top < maxSize-1) {
            top++;
            elements[top] = n;
            return true; // if success, returns true
        }
        return false; // false if the stack is full
    }

    public int pop() {
        if (top >= 0) { // checking if the stack is empty
            int data = elements[top];
            top = top -1;
            return data;
        } else {
            System.out.println("Error - Tried to pop from an empty stack");
            return -1;
        }
    }

    public int peek() {
        if (top >=0) {
            int data = elements[top];
            return data;
        } else {
            return -1;
        }
    }

    public boolean isEmpty(){
        int x = pop(); // checking if -1 is returned
        return (x == -1); // if -1 is returned then it's empty
    }

    public boolean isFull(){
        return !push(0); // if push returns status true, then it means the list isn't full, so we negate what it returns
    }

}