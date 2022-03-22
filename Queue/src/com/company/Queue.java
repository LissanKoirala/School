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
        last.setNext(new_node);
        last = new_node;
    }

    public int de_Queue() { // removes the item at the front of the queue
        int value = front.getValue(); // saves toe value to return it
        front = front.getNext(); // sets the front to the next in the queue
        remove(); // removes the front Node;
        return value;
    }

}
