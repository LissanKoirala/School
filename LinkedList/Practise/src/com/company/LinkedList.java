package com.company;

public class LinkedList {
    public Node head;

    public LinkedList() {
        head = null;
    }

    public LinkedList(int[] values) {
        head = new Node(values[0]);
        Node n = head;
        for (int i=1; i<values.length; i++) {
            Node adding = new Node(values[i]);
            n.setNext(adding);
            n = n.getNext();
        }
    }

    public void addItem(int v) {
        Node n = new Node(v);
        if (head == null) {
            head = n;
        } else {
            // walk the the list to the end
            Node current = head;
            Node previous = null;
            while (current != null) {
                previous = current;
                current = current.getNext();
            }
            previous.setNext(n);
        }
    }

    public void display(){
        // TODO : Go through all the nodes and display their value

        Node current = head;
        Node previous = null;
        while (current != null) {
            previous = current;
            current = current.getNext();
            // System.out.println(Current....);
        }

    }

}