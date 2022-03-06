package com.company;

public class Main {

    public static void main(String[] args) {
        FarmAnimal pig = new FarmAnimal();
        pig.display();

        Cow animal1 = new Cow("Daisy");
        Pig animal2 = new Pig("42");
        System.out.println(animal1.name + " says " + animal1.noise());
        System.out.println(animal2.name + " says " + animal2.noise());

        FarmAnimal animal3 = new Cow("Bess");
        System.out.println(animal3.name + " says " + animal3.noise());
        // Output: Bess says moo, we are creating a new instance of the class Cow which
        // extends from the class FarmAnimal, then we are calling the method noise()
        // which has been overwritten by the class Cow. animal3.name is just accessing
        // the name of the animal3 which is Bess, it can do so as it is a public
        // variable.
    }
}
