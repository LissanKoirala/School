package com.company;

public class Node {
    private int value;
    private Node next;

    public Node(int v) {
        value = v;
        next = null;
    }

    public void setNext(Node n) {
        next = n;
    }

    public Node getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int n){
        this.value = n;
    }
}