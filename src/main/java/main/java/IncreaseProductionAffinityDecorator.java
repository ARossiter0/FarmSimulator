package main.java;

public class IncreaseProductionAffinityDecorator extends AnimalDecorator {
    
    public IncreaseProductionAffinityDecorator(Animal source) {
        super(source);
    }
    
    @Override
    public boolean addProduct(int frequency) {
        return super.wrappee.addProduct(1);
    }
}
