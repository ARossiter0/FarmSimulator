package main.java;

public class Cow extends Animal {
    public Cow(String name) {
        super(name);
        this.cost = AnimalConstants.COW_COST;
        this.animalType = "Cow";
    }
    
    public Cow(String name, int age) {
        super(name, age);
        this.cost = AnimalConstants.COW_COST;
        this.animalType = "Cow";
    }
    
}
