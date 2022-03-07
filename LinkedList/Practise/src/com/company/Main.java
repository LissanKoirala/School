package com.company;

public class Main {
    public static void main(String[] args) {
        System.out.println("Linked List");
        // creating some nodes
        Node n1 = new Node(13);
        Node n2 = new Node(7);
        Node n3 = new Node(68);
        n1.setNext(n2);
        n2.setNext(n3);
        n1.display();
    }
}