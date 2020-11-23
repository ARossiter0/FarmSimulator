package main.java;

import java.util.ArrayList;
import java.util.Random;

public abstract class AnimalImpl implements Component, Animal {
    protected int cost;
    protected String animalType;
    protected ArrayList<Product> products;
    protected String productType;
    protected int productLimit;
    private boolean pregnant;
    private int age;
    private boolean diseased;
    private boolean full;
    private String name;
    private Mediator m;
    public boolean hasAffinity;
    
    public AnimalImpl(String name) {
        this.name = name;
        this.age = 0;
        this.products = new ArrayList<>();
    }
    
    public AnimalImpl(String name, int age) {
        this.name = name;
        this.age = age;
        this.products = new ArrayList<>();
    }
    
    public void setPregnant(boolean preg) {
        this.pregnant = preg;
    }
    
    public boolean isPregnant() {
        return this.pregnant;
    }
    
    public String getAnimalName() {
        return name;
    }
    
    public int getCost() {
        return cost;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getType() {
        return animalType;
    }
    
    public void incrementAge() {
        age++;
    }

    /** 
     * Attempts to add a product to this animal, returns false when animal is to its
     * productLimit. Also false if animal is too young, or age is not odd(animal produces
     * every 2 days)
     * 
     * @return
     */
    @Override
    public boolean addProduct(int frequency) {
        if (products.size() > productLimit) {
            full = true;
            return false;
        } else if (age < 3 || age % frequency != 0) {
            return false;
        }
        return this.products.add(new Product(productType));
    }
    
    public boolean collectProduct() {
        if (products.size() == 0) {
            return false;
        }
        m.collectProduct(products.remove(0));
        return true;
    }
    
    public int getProductSize() {
        return products.size();
    }
    
    /** 
     * Method called by farmManager on each animal if 2 or more of this type of
     * animal exists.
     * @return true if animal gets pregnant false if they do not
     */
    @Override
    public boolean tryToGetPregnant(int percentChance) {
        if (age % 4 == 0) {
            Random ran = new Random(System.nanoTime());
            if (ran.nextInt(100) < percentChance) {
                pregnant = true;
                return true;
            } 
        }
        return false;
    }
    
    /** 
     * Animal attempts to fight of its disease, has a 20% chance of dying
     * @return true if animal survives and false if animal dies
     */
    @Override
    public boolean fightOffDisease(int percentChance) {
        Random ran = new Random(System.nanoTime());
        if (ran.nextInt(100) < 20) {
            m.removeAnimal(this);
            return false;
        }
        return true;
    }
    
    @Override
    public void setMediator(Mediator mediator) {
        this.m = mediator;
    }
    
    @Override
    public String getName() {
        return "Animal";
    }
    
    public void setDiseased(boolean diseased) {
        this.diseased = diseased;
    }
    
    public boolean getDiseased() {
        return diseased;
    }
    
    public void setFull(boolean full) {
        this.full = full;
    }
    
    public boolean getFull() {
        return full;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public int getProductLimit() {
        return productLimit;
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }
    
    public void addAffinity() {
        this.hasAffinity = true;
    }
    
    public boolean hasAffinity() {
        return hasAffinity;
    }
    
}
