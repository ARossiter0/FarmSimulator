package main.java;

public class Pig extends Animal {
    public Pig(String name) {
        super(name);
        this.cost = AnimalConstants.PIG_COST;
        this.animalType = "Pig";
    }
    
    public Pig(String name, int age) {
        super(name, age);
        this.cost = AnimalConstants.PIG_COST;
        this.animalType = "Pig";
    }
}
