package main.java;

import java.util.Random;

public class InHeatAffinityDecorator extends AnimalDecorator {

    public InHeatAffinityDecorator(Animal source) {
        super(source);
    }
    
    @Override
    public boolean tryToGetPregnant(int percentChance) {
        return super.wrappee.tryToGetPregnant(percentChance + 25);
    }
}
