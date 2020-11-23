package main.java;

import java.util.ArrayList;

public class Farm implements Component {
    private int acreage;
    private int upgradeCost;
    private ArrayList<Product> products;
    private Mediator m;
    
    public Farm() {
        this.acreage = 1;
        this.upgradeCost = 5000;
        this.products = new ArrayList<>();
    }
    

    @Override
    public void setMediator(Mediator mediator) {
        this.m = mediator;
    }

    @Override
    public String getName() {
        return "Farm";
    }
    
    public int getAcreage() {
        return acreage;
    }
    
    public void upgradeFarm() {
        upgradeCost *= 1.50;
        acreage++;
    }
    
    public int getUpgradeCost() {
        return upgradeCost;
    }
    
    public void addProduct(Product product) {
        products.add(product);
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }
    
}
