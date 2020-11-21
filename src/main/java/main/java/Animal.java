package main.java;

public abstract class Animal {
    protected int cost;
    protected String animalType;
    private int age;
    private String name;
    
    public Animal(String name) {
        this.name = name;
        this.age = 0;
    }
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getAge() {
        return age;
    }
    
    public void incrementAge() {
        age++;
    }
    
    public String getType() {
        return animalType;
    }
    
}
