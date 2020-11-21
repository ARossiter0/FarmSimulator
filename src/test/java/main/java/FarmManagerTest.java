package main.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.AnimalFactory.AnimalType;

public class FarmManagerTest {

    @Test
    public void testBuyAnimal() {
        FarmManager testFarm = new FarmManager(5000);
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.COW));
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.PIG));
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.SHEEP));
    }
    
    @Test
    public void testBuyAnimalNoFunds() {
        FarmManager testFarm = new FarmManager(0);
        assertFalse(testFarm.buyAnimal("Bessie", AnimalType.COW));
    }
    
    @Test
    public void testBuyAnimalAtLimit() {
        FarmManager testFarm = new FarmManager(10000);
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.COW));
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.COW));
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.COW));
        assertTrue(testFarm.buyAnimal("Bessie", AnimalType.COW));
        assertFalse(testFarm.buyAnimal("Bessie", AnimalType.COW));
    }

}
