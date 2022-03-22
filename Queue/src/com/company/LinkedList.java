package com.company;

public class LinkedList {
    public Node head;
    private Node cursor;

    private Node last; // keeps track of the last at first when the class is instantiated
                       // so if a array of 1M entries is passed, it sets the last when it goes
                       // through it the first time, when the constructor is set.

    public LinkedList() {
        head = null;
        cursor = head;
    }

    public LinkedList(int[] values) {
        head = new Node(values[0]);
        cursor = head;
        Node n = head;
        for (int i=1; i<values.length; i++) {
            Node adding = new Node(values[i]);
            n.setNext(adding);
            n = n.getNext();
        }
    }

    public void clear(){
        Node current = head;
        head.setNext(null);
    }

    public void remove(){
        Node currenthead = head;
        head = currenthead.getNext();
    }
 
    public void remove(int index){
        Node current = head;
        Node previous = null;
        int counter = 0;
        while (current != null) {
            if(counter == index){
                previous.setNext(current.getNext());
                break;
            }
            previous = current;
            current = current.getNext();
            counter++;
        }
    }

    public int size(){
        Node current = head;
        int counter = 0;
        while (current != null) {
            current = current.getNext();
            counter++;
        }
        return counter;
    }

    public void addItem(int v) {
        Node n = new Node(v);
        if (head == null) {
            head = n;
            cursor = head;
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

    public void addFirst(int value){
        Node currenthead = head;
        Node newhead = new Node(value);
        newhead.setNext(currenthead);
        head = newhead;
    }

    public void add(int index, int value){
        Node current = head;
        Node previous = null;
        int counter = 0;
        while (current != null) {
            if(counter == index){
                Node newnode = new Node(value);
                previous.setNext(newnode);
                newnode.setNext(current);
                break;
            }
            previous = current;
            current = current.getNext();
            counter++;
        }
    }

    public Node get(int index){
        Node current = head;
        int counter = 0;
        while (current != null) {
            if(counter == index){
                return current;
            }
            current = current.getNext();
            counter++;
        }
        return null;
    }

    public Node getFirst(){
        return get(0);
    }

    public Node getLast(){
        int size = size();
        return get(size-1);
    }

    public void set(int index, int n){
        Node current = head;
        int counter = 0;
        while (current != null) {
            if(counter == index){
                current.setValue(n);
            }
            current = current.getNext();
            counter++;
        }
    }

    public boolean contains(int value){

//        short form but thought not the way you would want us to do.
//        int result = get(value);
//        if(result != -1){
//            return true;
//        }
//        return false;

        Node current = head;
        while (current != null) {
            if(value == current.getValue()){
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int indexOf(int value){
        Node current = head;
        int counter = 0;
        while (current != null) {
            if(current.getValue() == value){
                return counter;
            }
            current = current.getNext();
            counter++;
        }
        return -1;
    }

    public void display(){
        Node current = head;
        while (current != null) {
            System.out.println(current.getValue()); // displays the current node value
            current = current.getNext();
        }
    }


    // Stack code

    public int getNext() {
        int v = cursor.getValue();
        cursor = cursor.getNext();
        return v;
    }

    public void resetNext() {
        cursor = head;
    }

    public boolean hasNext() {
        // returns true if there is a valid node at cursor
        return (cursor != null);
    }

    public boolean isEmpty() {
        // return true if the list has no elements
        return (head == null);
    }
}