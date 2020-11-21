package main.java;

public class AnimalFactory {
    public Animal getAnimal(String name, AnimalType animal, boolean isBorn) {
        if (isBorn) {
            if (animal == null) {
                return null;
            }
            if (animal == AnimalType.COW) {
                return new Cow(name);
            }
            if (animal == AnimalType.PIG) {
                return new Pig(name);
            }
            if (animal == AnimalType.SHEEP) {
                return new Sheep(name);
            } 
        } else {
            if (animal == null) {
                return null;
            }
            if (animal == AnimalType.COW) {
                return new Cow(name, 3);
            }
            if (animal == AnimalType.PIG) {
                return new Pig(name, 3);
            }
            if (animal == AnimalType.SHEEP) {
                return new Sheep(name, 3);
            }
        }
        return null;
    }
    
    public enum AnimalType {
        PIG,
        COW,
        SHEEP;
    }
}
