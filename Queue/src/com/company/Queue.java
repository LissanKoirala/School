package com.company;

public class Queue extends LinkedList{
    private Node front;
    private Node last;

    public Queue(){
        super();
        front = getFirst();
        last = getLast();
    }

    public Queue(int[] array){
        super(array);
        front = getFirst();
        last = getLast();
    }


    public void en_Queue(int n) { // adds n to the rear of the queue
        Node new_node = new Node(n);
        last.setNext(new_node); // adding new node to the last
        last = new_node; // changing the last pointer to the new node
    }

    public int de_Queue() { // removes the item at the front of the queue
        int value = front.getValue(); // saves toe value to return it
        front = front.getNext(); // sets the front to the next in the queue
        remove(); // removes the front Node;
        return value; // no use of this, but was asked in the exercise
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    // Size is already implemented in LinkedList and this Queue inherits from it...

}