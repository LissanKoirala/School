package com.company;

public class Stack extends LinkedList{

    public Stack() {
        super();
    }

    public void push(int n) {
        addFirst(n);
    }

    public boolean pop() { // Removes the most-recently-pushed item from the stack and returns the status, true if pop was success...
        try {
            int value = head.getValue();
            head = head.getNext(); // sets the next to the current head, so no pointer is pointing to the first node, making java garbage collector remove it
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int peek() {
        return head.getValue();
    }

    public boolean isEmpty(){
        return !pop();
    }

    public int size(){
        return super.size();
    }
}