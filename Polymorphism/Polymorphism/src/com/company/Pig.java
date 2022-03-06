package com.company;

public class Pig extends FarmAnimal {

    public Pig(String name) {
        super(name);
    }

    public Pig() {
        super();
    }

    @Override // override decorator
    public String noise() { // overrides method noise from FarmAnimal
        return "oink";
    }
}
