package main.java;

import java.util.ArrayList;
import java.util.Random;

import main.java.AnimalFactory.AnimalType;

public class FarmManager implements Mediator {
    private Random ran;
    private Farm farm;
    private ArrayList<Farmer> farmers;
    private ArrayList<Animal> animals;
    private Bank bank;
    private int numPigs;
    private int numCows;
    private int numSheep;
    private int ticks;
    private boolean hasPregnant;

    public FarmManager(int startBalance) {
        this.ran = new Random(System.nanoTime());
        this.farm = new Farm();
        this.farmers = new ArrayList<>();
        this.animals = new ArrayList<>();
        this.bank = new Bank(startBalance);
        this.ticks = 0;
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
        incrementAnimalType(newAnimal.getType());
        newAnimal.setMediator(this);
        animals.add(newAnimal);
        return true;
    }
    
    public int getNumAnimalType(AnimalType type) {
        if (type.equals(AnimalType.PIG)) {
            return numPigs;
        }
        if (type.equals(AnimalType.SHEEP)) {
            return numSheep;
        }
        if (type.equals(AnimalType.COW)) {
            return numCows;
        }
        return 0;
    }
    
    public void incrementAnimalType(AnimalType type) {
        if (type.equals(AnimalType.PIG)) {
            numPigs++;
        }
        if (type.equals(AnimalType.SHEEP)) {
            numSheep++;
        }
        if (type.equals(AnimalType.COW)) {
            numCows++;
        }
    }
    
    public void decrementAnimalType(AnimalType type) {
        if (type.equals(AnimalType.PIG)) {
            numPigs--;
        }
        if (type.equals(AnimalType.SHEEP)) {
            numSheep--;
        }
        if (type.equals(AnimalType.COW)) {
            numCows--;
        }
    }

    public boolean hireFarmer(String name) {
        int limit = farm.getAcreage() * 10;
        if (farmers.size() == limit) {
            return false;
        }

        Farmer newFarmer = new Farmer(name);
        newFarmer.setMediator(this);
        farmers.add(newFarmer);
        return true;
    }

    public void fireFarmer(Farmer farmer) {
        farmers.remove(farmer);
    }

    public int getBalance() {
        return bank.getBalance();
    }

    public int getNumAnimals() {
        return animals.size();
    }
    
    public String getFarmerInfo() {
        String farmerString = "";
        for (int i = 0; i < farmers.size(); i++) {
            int tasksLeft = ( farmers.get(i).TASKSPERDAY - farmers.get(i).getTasksDone());
            farmerString += (i + 1) + ". " + farmers.get(i).getFarmerName() + " | Tasks left: " + tasksLeft + "\n";;
        }
        return farmerString;
    }

    public String getAnimalInfo() {
        String animalString = "";
        for (Animal a : animals) {
            animalString += a.getAnimalType() + ": " + a.getAnimalName() + " ";
            if (a.getDiseased()) {
                animalString += "(Diseased) ";
            }
            if (a.isPregnant()) {
                animalString += "(Pregnant) ";
            }
            animalString += a.getProductType().substring(0, 1).toUpperCase() + a.getProductType().substring(1) + ": "
                    + a.getProductSize();
            if (a.getProductType().equals(AnimalConstants.COW_PRODUCT)) {
                animalString += " gallons";
            }
            if (a.getProductType().equals(AnimalConstants.SHEEP_PRODUCT)) {
                animalString += " bolts";
            }
            if (a.getFull()) {
                animalString += "(Full)";
            }
            animalString += "\n";
        }
        return animalString;
    }

    public int getNumFarmers() {
        return farmers.size();
    }

    public int getFarmAcreage() {
        return farm.getAcreage();
    }

    @Override
    public void nextDay() {
        ticks++;
        // Tally up all products from previous day
        int total = 0;
        int wool = 0;
        int milk = 0;
        int truffles = 0;
        for (int i = 0; i < farm.getProducts().size(); i++) {
            Product p = farm.getProducts().remove(0);
            switch (p.getType()) {
            case AnimalConstants.SHEEP_PRODUCT:
                wool++;
            case AnimalConstants.COW_PRODUCT:
                milk++;
            case AnimalConstants.PIG_PRODUCT:
                truffles++;
            }
            total += p.getSellPrice();
        }
        bank.deposit(total);
        System.out.println("You have sold:\n" + wool + " bolts of wool\n" + milk + " gallons of milk\n" + truffles
                + " truffles\n\n" + "Total Profit: " + total + " Schrute bucks\n\n");

        

        // Update animals
        for (Animal a : animals) {
            // If animal was pregnant but did not get treated
            if (a.isPregnant()) {
                removeAnimal(a);
                System.out.println(
                        a.getAnimalName() + " the " + a.getAnimalType() + " died in the night in child birth");
            }
            
            // Check age to apply disease
            if (a.getDiseased()) {
                // If animal did not fight off disease
                if (!a.fightOffDisease(20)) {
                    System.out.println(
                            a.getAnimalName() + " the " + a.getAnimalType() + " died in the night due to its disease");
                }
            }
            
            a.incrementAge();
            
            // Check if animal is old enough to be diseased
            if (a.getAge() > 14 && !a.getDiseased()) {
                a.setDiseased(true);
            }
            
            // If there are more than one of this type of animal and animal is not diseased
            if (getNumAnimalType(a.getType()) > 1 && !a.getDiseased()) {
                // This animal has base 50 percent chance to get pregnant
                if (a.tryToGetPregnant(20)) {
                    hasPregnant = true;
                    System.out.println(
                            a.getAnimalName() + " the " + a.getAnimalType() + " became pregnant!\n"
                                    + "Send a farmer to care for them or they will die in childbirth");
                }
            }
            
            
            // Adds product to animal and returns false if animal cannot produce this day for some reason
            if (a.addProduct(2)) {
                System.out.println(a.getAnimalName() + " the " + a.getAnimalType() + " produced " + a.getProductType() + " today");
            }
            
            // Determine if farm can be upgraded
            if (bank.getBalance() >= farm.getUpgradeCost() * 1.20) {
                System.out.println("Your farm has been upgraded for " + farm.getUpgradeCost() + "\n" + "You now have "
                        + farm.getAcreage() + " acres!");
                bank.withdraw(farm.getUpgradeCost());
                farm.upgradeFarm();
                
            }
        }
        
        // Update farmers
        for (Farmer f : farmers) {
            f.resetTasks();
            if (bank.withdraw(Farmer.COSTPERDAY)) {
                System.out.println("You payed farmer " + f.getFarmerName() + " " + Farmer.COSTPERDAY);
            } else {
                System.out.println("You could not pay farmer " + f.getFarmerName() + " and he quit");
                farmers.remove(f);
            }
        }
        
    }
    
    public int getTicks() {
        return ticks;
    }
    
    
    public Farmer getFarmer(String name) {
        for (Farmer f : farmers) {
            if (f.getFarmerName().toLowerCase().equals(name.toLowerCase())) {
                return f;
            }
        }
        return null;
    }
    
    public String getPregnantAnimals() {
        for (Animal a : animals) {
            if (a.isPregnant()) {
                return a.getAnimalName() + "\n";
            }
        }
        return null;
    }
    
    public boolean animalBorn(Animal animal, String name) {
        AnimalFactory market = new AnimalFactory();

        int limit = farm.getAcreage() * 4;
        if (animals.size() == limit) {
            bank.deposit(200);
            return false;
        }
        animal.setPregnant(false);
        Animal newAnimal = market.getAnimal(name, animal.getType(), true);
        incrementAnimalType(newAnimal.getType());
        newAnimal.setMediator(this);
        animals.add(newAnimal);
        return true;
    }
    
    public Animal getAnimal(String name) {
        for (Animal a : animals) {
            if (a.getAnimalName().toLowerCase().equals(name.toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void removeAnimal(Animal a) {
        decrementAnimalType(a.getType());
        animals.remove(a);
    }


    @Override
    public void collectProduct(Product p) {
        farm.addProduct(p);
    }
    
    public boolean hasPregnantAnimals() {
        return hasPregnant;
    }
    
    public void addAffinity(Animal animal, String affinity) {
        int i = animals.indexOf(animal);
        Animal a = animals.get(i);
        if (affinity.equals("immunity")) {
            a = new ImmunityAffinityDecorator(new AnimalDecorator(animals.get(i)));
        }
        if (affinity.equals("increase_production")) {
            a = new IncreaseProductionAffinityDecorator(new AnimalDecorator(animal));
        }
        if (affinity.equals("inheat")) {
            a = new InHeatAffinityDecorator(new AnimalDecorator(animal));
        }
        animals.set(i, a);
    }
}
