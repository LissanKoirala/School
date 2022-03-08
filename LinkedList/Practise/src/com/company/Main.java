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
        int[] data = {1,2,3,4,5};
        LinkedList myList = new LinkedList(data);
        myList.head.display();
        myList.head.getNext().display();
        myList.head.getNext().getNext().display();
        myList.head.getNext().getNext().getNext().display();
        myList.head.getNext().getNext().getNext().getNext().display();
    }
}