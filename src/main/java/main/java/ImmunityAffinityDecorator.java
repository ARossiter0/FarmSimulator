package main.java;

import java.util.Random;

public class ImmunityAffinityDecorator extends AnimalDecorator {

    public ImmunityAffinityDecorator(Animal source) {
        super(source);
    }
    
    @Override
    public boolean fightOffDisease(int percentChance) {
        return super.wrappee.fightOffDisease(percentChance + 10);
    }

}
