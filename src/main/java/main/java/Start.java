package main.java;

import java.util.Scanner;

import main.java.AnimalFactory.AnimalType;

public class Start {
    private static FarmManager farmManager;
    private static Scanner in = new Scanner(System.in);
    private static boolean gameStarted = false;
    private static boolean dayStarted = false;

    public static void main(String[] args) {
        System.out.println("WELCOME TO ANIMAL FARM!\n" + "-------------------------------\n"
                + "To start a new farm, choose a difficulty:\n" + "\n" + "1. Easy:   Start with 5000 Schrute Bucks\n"
                + "2. Medium: Start with 2500 Schrute Bucks\n" + "3. Hard:   Start with 1000 Schrute Bucks");
        int option = -1;
        try {
            option = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, try again");
        }
        String difficulty;
        switch (option) {
        case 1:
            farmManager = new FarmManager(5000);
            difficulty = "easy";
            break;
        case 2:
            farmManager = new FarmManager(2500);
            difficulty = "medium";
            break;
        case 3:
            farmManager = new FarmManager(1000);
            difficulty = "hard";
            break;
        default:
            farmManager = new FarmManager(5000);
            difficulty = "easy";
        }
        System.out.println("Starting new game on " + difficulty + " difficulty");
        runGame();
    }

    public static void runGame() {
        while (!gameStarted) {
            System.out.println("FARM SETUP\n" + "-------------------------------------------------------------\n"
                    + "Choose an option:\n" + "1. Buy animals to make money on your farm (limit 4 per acre)\n"
                    + "2. Hire farmers to work on your farm (limit 10 per acre)\n" + "3. Start you first day\n"
                    + "-------------------------------------------------------------\n" + "Current Funds: "
                    + farmManager.getBalance() + "\n" + "Number of acres: " + farmManager.getFarmAcreage() + "\n" + "Total Animals: " + farmManager.getNumAnimals() + "\n"
                    + "Total Farmers: " + farmManager.getNumFarmers());
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            switch (option) {
            case 1:
                buyAnimals();
                break;
            case 2:
                hireFarmers();
                break;
            case 3:
                gameStarted = true;
                dayTime();
                break;
            default:
                System.out.print("Please choose a valid option");
            }
        }

    }

    public static void buyAnimals() {
        boolean done = false;
        while (!done) {
            System.out.println("Animals on your farm | Balance " + farmManager.getBalance() + "\n"
                    + "-------------------------------------------------------------\n" + farmManager.getAnimalInfo()
                    + "\n" + "-------------------------------------------------------------\n"
                    + "Which animal would you like to buy?\n" + "1. Pig (500 Schrute bucks)\n"
                    + "2. Cow (1000 Schrute bucks)\n" + "3. Sheep (1500 Schrute bucks)\n" + "4. Done");
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            AnimalType aType = null;
            String type = "";
            switch (option) {
            case 1:
                aType = AnimalType.PIG;
                type = "pig";
                break;
            case 2:
                aType = AnimalType.COW;
                type = "cow";
                break;
            case 3:
                aType = AnimalType.SHEEP;
                type = "sheep";
                break;
            case 4:
                done = true;
                break;
            }
            if (aType != null) {
                System.out.println("Choose a name for your new " + type);
                String name = in.nextLine();
                if (!farmManager.buyAnimal(name, aType)) {
                    System.out.println("Your farm is full, you cannot fit more animals");
                    done = true;
                }
            }
        }
        if (!gameStarted) {
            runGame();
        } else {
            dayTime();
        }
    }

    public static void hireFarmers() {
        boolean done = false;
        while (!done) {
            System.out.println("You have " + farmManager.getNumFarmers() + " farmers\n"
                    + "-------------------------------------------------------------\n"
                    + "Farmers cost 200 Schrute bucks a day\n" + "Farmers can perform 2 tasks per day\n"
                    + "Tasks include:\n" + " -Collecting animal product\n" + " -Assisting in animal birth\n"
                    + "If you cannot pay them, they will quit\n" + "\n"
                    + "Press 1 to hire new Farmer, 2 if you are done");
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            switch (option) {
            case 1:
                System.out.println("Please enter a name for your farmer");
                String name = in.nextLine();
                if (farmManager.hireFarmer(name)) {
                    System.out.println("You have successfully hired " + name);
                } else {
                    System.out.println("You have reached your limit of farmers, wait until your farm upgrades");
                    done = true;
                }
                break;
            case 2:
                done = true;
                break;
            }
        }
        if (!gameStarted) {
            runGame();
        } else {
            dayTime();
        }
    }
    
    public static void fireFarmer() {
        System.out.println("Please type the name of the farmer you would like to fire\n"
                + "-------------------------------------------------------------\n");
        System.out.println(farmManager.getFarmerInfo());
        System.out.println("-------------------------------------------------------------\n");
        String name = in.nextLine();
        Farmer farmer = farmManager.getFarmer(name);
        if (farmer == null) {
            System.out.println("Farmer not found");
        }
        farmManager.fireFarmer(farmer);
        
    }
    
    public static void doFarmerTasks() {
        boolean done = false;
        while (!done) {
            System.out.println("Please type the name of the farmer you would like to send on a task\n"
                    + "Type done to exit\n"
                    + "-------------------------------------------------------------\n");
            System.out.println(farmManager.getFarmerInfo());
            System.out.println("-------------------------------------------------------------\n");
            String name = in.nextLine();
            if (name.toLowerCase().equals("done")) {
                break;
            }
            Farmer farmer = farmManager.getFarmer(name);
            if (farmer == null) {
                System.out.println("Farmer not found");
            } else if (farmer.getTasksDone() == Farmer.TASKSPERDAY) {
                System.out.println("This farmer has no more tasks left\n");
            } else {
                doTask(farmer);
            }
        }
    }
    
    public static void doTask(Farmer farmer) {
        boolean done = false;
        while (!done) {
            System.out.println("Which task would you like " + farmer.getFarmerName() + " to do?\n"
                    + "Tasks left: " + (Farmer.TASKSPERDAY - farmer.getTasksDone()) + "\n"
                    + "-------------------------------------------------------------\n"
                    + "1. Collect product from animal\n"
                    + "2. Train animal (Add affinity)\n");
            if (farmManager.hasPregnantAnimals()) {
                System.out.println("3. Tend to pregnant animals");
            }
            System.out.println("4. Done");
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            if (farmer.getTasksDone() == Farmer.TASKSPERDAY) {
                System.out.println("This farmer has no more tasks left\n");
                option = 4;
            }
            switch (option) {
            case 1:
                if (collectProduct()) {
                    farmer.doTask();
                }
                break;
            case 2:
                if (addAffinity()) {
                    farmer.doTask();
                }
                break;
            case 3:
                if (farmManager.hasPregnantAnimals()) {
                    if (assistBirth()) {
                        farmer.doTask();
                    } else {
                        System.out.println("You did not have room on your farm for a new animal \n"
                                + "You sold it for 200 Schrute bucks");                        
                    }
                } else {
                    System.out.println("Invalid input, try again");
                }
                break;
            case 4: 
                done = true;
                break;
            }
        }
    }
    
    public static boolean assistBirth() {
        System.out.println("-------------------------------------------------------------\n" 
                + farmManager.getPregnantAnimals() + "\n" 
                + "-------------------------------------------------------------\n"
                + "Enter the name of the animal you would like to help give birth\n"
                + "Type done to exit");
                String name = in.nextLine();
                if (name.toLowerCase().equals("done")) {
                    return false;
                }
                Animal animal = farmManager.getAnimal(name);
                if (animal == null) {
                    System.out.println("Animal not found");
                } else if (farmManager.animalBorn(animal, animal.getAnimalName() + " jr.")) {
                    System.out.println("Congratulations " + animal.getAnimalName() + " jr. has been born");
                    return true;
                } else {
                    return false;
                }
                return false;
    }
    
    public static boolean addAffinity() {
        System.out.println("-------------------------------------------------------------\n" 
                + farmManager.getAnimalInfo() + "\n" 
                + "-------------------------------------------------------------\n"
                + "Enter the name of the animal you would like to add affinity to\n"
                + "Type done to exit");
        String name = in.nextLine();
        if (name.toLowerCase().equals("done")) {
            return false;
        }
        Animal animal = farmManager.getAnimal(name);
        if (animal == null) {
            System.out.println("Animal not found");
        } else {
            System.out.println("Adding affinity to: " + animal.getAnimalName() + " the " + animal.getAnimalType() + "\n"
                    + "-------------------------------------------------------------\n"
                    + "Available affinities: \n"
                    + "1. Immunity (Less chance to die from disease)\n"
                    + "2. Ambitious (Production of product increases to once per day)\n"
                    + "3. In heat (Higher chance of getting pregnant)\n");
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            switch (option) {
            case 1: 
                farmManager.addAffinity(animal, "immunity");
                System.out.println(animal.getAnimalName() + " the " + animal.getAnimalType() + " now has immunity");
                return true;
            case 2:
                farmManager.addAffinity(animal, "increase_production");
                System.out.println(animal.getAnimalName() + " the " + animal.getAnimalType() + " is now ambitious");
                return true;
            case 3:
                farmManager.addAffinity(animal, "inheat");
                System.out.println(animal.getAnimalName() + " the " + animal.getAnimalType() + " is now in heat");
                return true;
            }
        }
        return false;
    }
    
    public static boolean collectProduct() {
        System.out.println("-------------------------------------------------------------\n" 
        + farmManager.getAnimalInfo() + "\n" 
        + "-------------------------------------------------------------\n"
        + "Enter the name of the animal you would like to collect from\n"
        + "Type done to exit");
        String name = in.nextLine();
        if (name.toLowerCase().equals("done")) {
            return false;
        }
        Animal animal = farmManager.getAnimal(name);
        if (animal == null) {
            System.out.println("Animal not found");
        } else if (animal.collectProduct()) {
            System.out.println("Collected " + animal.getProductType() + " from " + animal.getAnimalName());
            return true;
        } else {
            System.out.println(animal.getAnimalName() + " the " + animal.getAnimalType() + " has no " + animal.getProductType() + " to collect");
            return false;
        }
        return false;
    }
    

    public static void dayTime() {
        while (gameStarted) {
            if (!dayStarted) {
                System.out.println("GOOD MORNING! Here's your morning update\n"
                        + "-------------------------------------------------------------\n");             
                farmManager.nextDay();
                dayStarted = true;
                
            }
            System.out.println("-------------------------------------------------------------\n");
            System.out.println("Animal Status:");
            System.out.println(farmManager.getAnimalInfo());
            System.out.println("Farmers " + farmManager.getNumFarmers());
            System.out.println("-------------------------------------------------------------\n");
            System.out.println("What would you like to do? | Balance: " + farmManager.getBalance() + "\n"
                    + "1. Buy animals\n"
                    + "2. Hire farmer\n"
                    + "3. Fire farmer\n"
                    + "4. Send farmers to do tasks\n"
                    + "5. End the day");
            int option = -1;
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            switch (option) {
            case 1: 
                buyAnimals();
                break;
            case 2:
                hireFarmers();
                break;
            case 3:
                fireFarmer();
                break;
            case 4:
                doFarmerTasks();
                break;
            case 5:
                dayStarted = false;
                dayTime();
                break;
            }
        }
        if (farmManager.getBalance() == 100000) {
            gameStarted = false;
            System.out.println("You WIN!");
        }
    }
}
