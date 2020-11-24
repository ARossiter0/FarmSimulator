package main.java;

import java.util.ArrayList;
import java.util.Random;

import main.java.AnimalFactory.AnimalType;

public interface Animal {
    public void setPregnant(boolean preg);

    public boolean isPregnant();

    public String getAnimalName();

    public int getCost();

    public int getAge();

    public AnimalType getType();

    public void incrementAge();

    public boolean addProduct(int frequency);

    public boolean collectProduct();

    public int getProductSize();

    public boolean tryToGetPregnant(int percentChance);

    public boolean fightOffDisease(int percentChance);

    public void setMediator(Mediator mediator);

    public String getName();

    public void setDiseased(boolean diseased);

    public boolean getDiseased();

    public void setFull(boolean full);

    public boolean getFull();

    public String getProductType();

    public int getProductLimit();

    public ArrayList<Product> getProducts();
    
    public String getAnimalType();
}
