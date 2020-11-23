package main.java;

public class Sheep extends AnimalImpl {
    
    public Sheep(String name) {
        super(name);
        this.cost = AnimalConstants.SHEEP_COST;
        this.animalType = "Sheep";
        this.productType = AnimalConstants.SHEEP_PRODUCT;
        this.productLimit = AnimalConstants.SHEEP_PRODUCT_LIMIT;
    }
    
    public Sheep(String name, int age) {
        super(name, age);
        this.cost = AnimalConstants.SHEEP_COST;
        this.animalType = "Sheep";
        this.productType = AnimalConstants.SHEEP_PRODUCT;
        this.productLimit = AnimalConstants.SHEEP_PRODUCT_LIMIT;
    }
    
}
