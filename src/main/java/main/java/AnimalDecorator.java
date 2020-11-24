package main.java;

import java.util.ArrayList;

import main.java.AnimalFactory.AnimalType;

public class AnimalDecorator implements Animal {
    protected Animal wrappee;
    
    public AnimalDecorator(Animal source) {
        this.wrappee = source;
    }

    @Override
    public boolean addProduct(int frequency) {
        return wrappee.addProduct(frequency);
    }

    @Override
    public boolean tryToGetPregnant(int percentChance) {
        return wrappee.tryToGetPregnant(percentChance);
    }

    @Override
    public boolean fightOffDisease(int percentChance) {
        return wrappee.fightOffDisease(percentChance);
    }
    
    @Override
    public void setPregnant(boolean preg) {
        wrappee.setPregnant(preg);
        
    }

    @Override
    public boolean isPregnant() {
        return wrappee.isPregnant();
    }

    @Override
    public String getAnimalName() {
        return wrappee.getAnimalName();
    }

    @Override
    public int getCost() {
        return wrappee.getCost();
    }

    @Override
    public int getAge() {
        return wrappee.getAge();
    }

    @Override
    public AnimalType getType() {
        return wrappee.getType();
    }

    @Override
    public void incrementAge() {
        wrappee.incrementAge();
    }

    @Override
    public boolean collectProduct() {
        return wrappee.collectProduct();
    }

    @Override
    public int getProductSize() {
        return wrappee.getProductSize();
    }

    @Override
    public void setMediator(Mediator mediator) {
        wrappee.setMediator(mediator);
    }

    @Override
    public String getName() {
        return wrappee.getName();
    }

    @Override
    public void setDiseased(boolean diseased) {
        wrappee.setDiseased(diseased);
    }

    @Override
    public boolean getDiseased() {
        return wrappee.getDiseased();
    }

    @Override
    public void setFull(boolean full) {
        wrappee.setFull(full);
    }

    @Override
    public boolean getFull() {
        return wrappee.getFull();
    }

    @Override
    public String getProductType() {
        return wrappee.getProductType();
    }

    @Override
    public int getProductLimit() {
        return wrappee.getProductLimit();
    }

    @Override
    public ArrayList<Product> getProducts() {
        return wrappee.getProducts();
    }

    @Override
    public String getAnimalType() {
        return wrappee.getAnimalType();
    }
}
