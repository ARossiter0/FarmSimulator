package main.java;

public class Farmer {
    public final int COSTPERDAY = 500;
    public final int TASKSPERDAY = 2;
    private String name;
    
    public Farmer(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
