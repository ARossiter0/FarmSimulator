package main.java;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        System.out.println("WELCOME TO ANIMAL FARM!\n"
                         + "-------------------------------\n"
                         + "To start a new farm, choose a difficulty\n"
                         + "Easy: Start with 5000 Schrute Bucks\n"
                         + "Medium: Start with 2500 Schrute Bucks\n"
                         + "Hard: Start with 1000 Schrute Bucks");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        input = input.toLowerCase();
        switch(input)
        case "easy":
        case "medium":
        case "hard":
    }
}
