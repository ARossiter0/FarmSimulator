package main.java;

public class Product {
    private int sellPrice;
    private String type;
    
    public Product(String type) {
        this.type = type;
        if (type.equals("wool")) {
            sellPrice = 100;
        }
        if (type.equals("milk")) {
            sellPrice = 200;
        }
        if (type.equals("truffles")) {
            sellPrice = 500;
        }
    }
    public int getSellPrice() {
        return sellPrice;
    }
    public String getType() {
        return type;
    }
}
