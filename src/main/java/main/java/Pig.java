package main.java;

import main.java.AnimalFactory.AnimalType;

public class Pig extends AnimalImpl {
    public Pig(String name) {
        super(name);
        this.cost = AnimalConstants.PIG_COST;
        this.animalType = AnimalType.PIG;
        this.productType = AnimalConstants.PIG_PRODUCT;
        this.productLimit = AnimalConstants.PIG_PRODUCT_LIMIT;
    }
    
    public Pig(String name, int age) {
        super(name, age);
        this.cost = AnimalConstants.PIG_COST;
        this.animalType = AnimalType.PIG;
        this.productType = AnimalConstants.PIG_PRODUCT;
        this.productLimit = AnimalConstants.PIG_PRODUCT_LIMIT;
    }
    
    
}
