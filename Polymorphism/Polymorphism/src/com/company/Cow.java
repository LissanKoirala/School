package com.company;

public class Cow extends FarmAnimal {

    public Cow(String name) {
        super(name);
    }

    public Cow(){
        super();
    }

    @Override // override decorator
    public String noise() { // overrides method noise from FarmAnimal
        return "moo";
    }

}
