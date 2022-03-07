package com.company;

class Main {
    public static void main(String[] args) {
        System.out.println("Linked list demo!");
        // create some nodes
        Node n1 = new Node(13);
        Node n2 = new Node(7);
        Node n3 = new Node(68);
        n1.setNext(n2);
        n2.setNext(n3);
        n1.display();

        // create a linked list
        LinkedList myList = new LinkedList();
        myList.addItem(12);
        myList.addItem(6);
        myList.addItem(67);
        myList.head.display();
        myList.head.getNext().display();
        myList.head.getNext().getNext().display();
    }
}