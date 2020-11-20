package main.java;

import java.util.LinkedList;

public class Farm {
    private int acreage;
    LinkedList<Farmer> farmers;
    LinkedList<Animal> animals;
    Bank bank;
    
    public Farm(int startBalance) {
        this.bank = new Bank(startBalance);
    }
}
