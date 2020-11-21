package main.java;

import java.util.ArrayList;
import main.java.AnimalFactory.AnimalType;

public class FarmManager {
    private Farm farm;
    private ArrayList<Farmer> farmers;
    private ArrayList<Animal> animals;
    private Bank bank;
    private DayTicker ticker;
    
    public FarmManager(int startBalance) {
        this.farm = new Farm();
        this.farmers = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.bank = new Bank(startBalance);
        this.ticker = new DayTicker();
    }
    
    public boolean buyAnimal(String name, AnimalType animal) {
        AnimalFactory market = new AnimalFactory();
        
        int limit = farm.getAcreage() * 4;
        if (animals.size() == limit) {
            return false;
        }
        
        Animal newAnimal = market.getAnimal(name, animal, false);
        boolean hasFunds = bank.withdraw(newAnimal.getCost());
        if (!hasFunds) {
            return false;
        }
        animals.add(newAnimal);
        return true;
    }
    
    public boolean hireFarmer(String name) {
        int limit = farm.getAcreage() * 10;
        if (farmers.size() == limit) {
            return false;
        }
        
        Farmer newFarmer = new Farmer(name);
        farmers.add(newFarmer);
        return true;
    }
    
    public boolean fireFarmer() {
        if (farmers.size() == 0) {
            return false;
        }
        farmers.remove(0);
        return true;
    }
    
    public int getBalance() {
        return bank.getBalance();
    }
    
    public int getNumAnimals() {
        return animals.size();
    }
    
    public String getAnimalInfo() {
        String animalString = "";
        int col = 0;
        for (Animal iter: animals) {
            animalString += iter.getType() + ": " + iter.getName() + "   ";
            col++;
            if (col == 4) {
                animalString += "\n";
                col = 0;
            }
        }
        return animalString;
    }
    
    public int getNumFarmers() {
        return farmers.size();
    }
}
