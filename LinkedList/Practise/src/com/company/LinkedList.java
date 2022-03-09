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
            // walk the list to the end
            Node current = head;
            Node previous = null;
            while (current != null) {
                previous = current;
                current = current.getNext();
            }
            previous.setNext(n);
        }
    }

    public int get(int index){
        Node current = head;
        Node previous = null;
        int counter = 0;
        while (current != null) {
            if(counter == index){
                return current.getValue();
            }
            previous = current;
            current = current.getNext();
            counter++;
        }
        return -1;
    }

    public void set(int index, int n){
        Node current = head;
        Node previous = null;
        int counter = 0;
        while (current != null) {
            if(counter == index){
                current.setValue(n);
            }
            previous = current;
            current = current.getNext();
            counter++;
        }
    }

    public void display(){
        Node current = head;
        Node previous = null;
        while (current != null) {
            System.out.println(current.getValue()); // displays the current node value
            previous = current;
            current = current.getNext();
        }
    }
}