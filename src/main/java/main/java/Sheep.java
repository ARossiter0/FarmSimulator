package main.java;

public class Sheep extends Animal {
    public Sheep(String name) {
        super(name);
        this.cost = AnimalConstants.SHEEP_COST;
        this.animalType = "Sheep";
    }
    
    public Sheep(String name, int age) {
        super(name, age);
        this.cost = AnimalConstants.SHEEP_COST;
        this.animalType = "Sheep";
    }
}
