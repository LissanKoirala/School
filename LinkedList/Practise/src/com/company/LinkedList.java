package com.company;

public class LinkedList {
    public Node head;

    public LinkedList() {
        head = null;
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
}