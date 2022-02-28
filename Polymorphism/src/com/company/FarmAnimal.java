package com.company;

public class FarmAnimal {
    public String name;
    private int serialNumber = 0;

    public FarmAnimal(String name) {
        this.name = name;
        serialNumber = -1; // deliberate negative, only positive int are valid
    }

    public FarmAnimal() {
        long time = System.currentTimeMillis(); // returns current time in milliseconds
        this.serialNumber = (int) time;
        this.name = "NULL"; // default unspecified name
    }

    public void display() {
        if (serialNumber != -1) {
            System.out.println(serialNumber);
        }
        if (!"NULL".equals(name)) { // if name doesn't equal NULL, prints it as well
            System.out.println(name);
        }
    }

    public String noise() {
        return "a vague snuffling noise";
    }

}
