package main.java;

import java.util.Scanner;

import main.java.AnimalFactory.AnimalType;

public class Start {
    private static FarmManager farmManager;
    private static Scanner in = new Scanner(System.in);
    private static boolean gameStarted = false;
    
    public static void main(String[] args) {
        System.out.println("WELCOME TO ANIMAL FARM!\n"
                         + "-------------------------------\n"
                         + "To start a new farm, choose a difficulty:\n"
                         + "\n"
                         + "Easy:   Start with 5000 Schrute Bucks\n"
                         + "Medium: Start with 2500 Schrute Bucks\n"
                         + "Hard:   Start with 1000 Schrute Bucks");
        String input = in.nextLine();
        input = input.toLowerCase();
        switch(input) {
            case "easy":
                farmManager = new FarmManager(5000);
                break;
            case "medium":
                farmManager = new FarmManager(2500);
                break;
            case "hard":
                farmManager = new FarmManager(1000);
                break;
            default:
                farmManager = new FarmManager(5000); 
                input = "easy";
        }
        System.out.println("Starting new game on " + input + " mode");
        runGame();
    }
    
    public static void runGame() {
        while (!gameStarted) {
            System.out.println("FARM SETUP\n" + "-------------------------------------------------------------\n"
                    + "Choose an option:\n" + "1. Buy animals to make money on your farm (limit 4 per acre)\n"
                    + "2. Hire farmers to work on your farm (limit 10 per acre)\n" + "3. Start you first day\n"
                    + "-------------------------------------------------------------\n" + "Current Funds: "
                    + farmManager.getBalance() + "\n" + "Total Animals: " + farmManager.getNumAnimals() + "\n"
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
            }
        }
        
    }
    public static void buyAnimals() {
        boolean done = false;
        while (!done) {
            System.out.println("Animals on your farm\n"
                    + "-------------------------------------------------------------\n" 
                    + farmManager.getAnimalInfo() + "\n" 
                    + "-------------------------------------------------------------\n"
                    + "Which animal would you like to buy?\n" 
                    + "1. Pig (500 Schrute bucks)\n"
                    + "2. Cow (1000 Schrute bucks)\n" 
                    + "3. Sheep (1500 Schrute bucks)\n" 
                    + "4. Done");
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
        }
    }
    
    public static void hireFarmers() {
        boolean done = false;
        while (!done) {
            System.out.println("You have " + farmManager.getNumFarmers() + " farmers\n"
                    + "-------------------------------------------------------------\n" 
                    + "Farmers cost 500 Schrute bucks a day\n"
                    + "Farmers can perform 2 tasks per day\n"
                    + "Tasks include:\n"
                    + " -Collecting animal product\n"
                    + " -Assisting in animal birth\n"
                    + " -Setting traps for predators\n"
                    + " -Hunting predators (Farmers may be killed by predators when hunting)\n"
                    + "If you cannot pay them, they will quit\n"
                    + "\n"
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
        }
    }
    
    public static void dayTime() {
        
    }
    
    public static void nightTime() {
        
    }
}
